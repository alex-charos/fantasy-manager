class PlayerList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            goalkeepers: [],
            defenders: [],
            midfielders: [],
            attackers: []

        };
    }

    componentDidUpdate = (prevProps) => {
        if (this.props.players !== prevProps.players) {
            let goalkeepers = [];
            let defenders = [];
            let midfielders = [];
            let attackers = [];

            if (this.props.players !== undefined) {
                goalkeepers = this.props.players.filter(p => {return p.position === "GOALKEEPER"});
                defenders = this.props.players.filter(p => {return p.position === "DEFENDER"});
                midfielders = this.props.players.filter(p => {return p.position === "MIDFIELDER"});
                attackers = this.props.players.filter(p => {return p.position === "ATTACKER"});
            }

            this.setState({
                goalkeepers: goalkeepers,
                defenders: defenders,
                midfielders: midfielders,
                attackers: attackers
            });
        }
    }

    render() {
        if (this.props.players) {
            return (
                <div >
                    <div className="text-center font-mono"> {this.props.head} </div>
                    <div className = "grid grid-cols-4 gap-4 divide-x-2 ">
                        <div className = "column-1  ">
                           <PlayerGroup players={this.state.goalkeepers} group="Goalkeepers" onPlayerClick={this.props.onPlayerClick} />
                        </div>
                        <div className = "column-1">
                            <PlayerGroup players={this.state.defenders} group="Defenders" onPlayerClick={this.props.onPlayerClick} />
                        </div>
                        <div className = "column-1">
                            <PlayerGroup players={this.state.midfielders} group="Midfielders" onPlayerClick={this.props.onPlayerClick} />
                        </div>
                        <div className = "column-1">
                            <PlayerGroup players={this.state.attackers} group="Attackers" onPlayerClick={this.props.onPlayerClick} />
                        </div>
                    </div>
                </div>
        );
    } else {
        return (<div>Please select Team to show players</div>);
    }
    }
}

class StartingSquad extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            goalkeepers: [],
            defenders: [],
            midfielders: [],
            attackers: []

        };
    }
     componentDidUpdate = (prevProps) => {
            if (this.props.players !== prevProps.players) {
                let goalkeepers = [];
                let defenders = [];
                let midfielders = [];
                let attackers = [];

                if (this.props.players !== undefined) {
                    goalkeepers = this.props.players.filter(p => {return p.position === "GOALKEEPER"});
                    defenders = this.props.players.filter(p => {return p.position === "DEFENDER"});
                    midfielders = this.props.players.filter(p => {return p.position === "MIDFIELDER"});
                    attackers = this.props.players.filter(p => {return p.position === "ATTACKER"});
                }

                 this.setState({
                    goalkeepers: goalkeepers,
                    defenders: defenders,
                    midfielders: midfielders,
                    attackers: attackers
                });
            }
        }

    render() {
      if (this.props.players && this.props.players.length >0) {
                return (


                        <div className = "grid grid-rows-5 gap-4">
                        <div className="text-center font-mono"> Starting 11  </div>
                             <div className = "flex place-content-center">
                                { this.state.goalkeepers.map((p)=>(
                                    <PlayerAvatar key={p.id} player={p} onPlayerClick={this.props.onPlayerClick} />
                                 )) }
                            </div>
                            <div className = "flex justify-between space-x-4">
                                 { this.state.defenders.map((p)=>(
                                    <PlayerAvatar key={p.id} player={p} onPlayerClick={this.props.onPlayerClick} />
                                    )) }
                            </div>
                            <div className = "flex  place-content-center space-x-4">
                                 { this.state.midfielders.map((p)=>(
                                    <PlayerAvatar key={p.id} player={p} onPlayerClick={this.props.onPlayerClick} />
                                  )) }
                            </div>
                            <div className = "flex  place-content-center space-x-4">
                                 { this.state.attackers.map((p)=>(
                                    <PlayerAvatar key={p.id} player={p} onPlayerClick={this.props.onPlayerClick} />
                                   )) }
                            </div>
                        </div>

            );
        } else {
            return (<div>Please select Team to show starting 11 </div>);
        }
    }

}
class PlayerGroup extends React.Component {

    render() {
     return (
                <div className= "divide-y-2">
                    <div className="font-mono"> {this.props.group} </div>
                    <div>
                    { this.props.players.map((p)=>(
                        <Player key={p.id} player={p} onPlayerClick={this.props.onPlayerClick} />

                     )) }
                     </div>
                </div>
                );

    }

}
class PlayerAvatar extends React.Component {
    render() {
        return (<div  className="rounded-full border-2 border-white-100 flex place-content-center" key={this.props.player.id} onClick={()=>this.props.onPlayerClick(this.props.player)} >
                  <div className=" rounded-full flex justify-center items-center text-sm">  {this.props.player.name} </div>
                  <div className="w-6 h-6 bg-red-600 text-lg place-content-center rounded-full border-2 border-white-100 text-center"> - </div>

                </div>);
        }
}


class Player extends React.Component {
    render() {
        return (<div key={this.props.player.id} onClick={()=>this.props.onPlayerClick(this.props.player)} >
                                        {this.props.player.name}
                                     </div>);
        }
}

class Team extends React.Component {
    render() {
        return (<div >
                    <div>{this.props.name}</div>
                    <div><img src={this.props.crest} /></div>
                </div>);

    }
}

class TeamsList extends React.Component {
  render() {
    if (this.props.teams) {
    return (
            <div>
                <select className="block py-2.5 px-0 w-full text-sm text-gray-500 bg-transparent border-0 border-b-2 border-gray-200 appearance-none dark:text-gray-400 dark:border-gray-700 focus:outline-none focus:ring-0 focus:border-gray-200 peer" onChange={(event)=>this.props.onTeamSelect(event.target.value)} >
                    <option key="noselect" value ="noselect">Select Team</option>
                    {
                        this.props.teams.map((p)=>(
                            <option key = {p.id} value={p.id}  >
                                       {p.name}
                            </option>
                        ))
                      }
                 </select>
             </div>
    );
    } else {
        retucn (<div>No Team Selected </div>);
    }
  }
}

class FixturesList extends React.Component {
  render() {
   if (this.props.fixtures) {
    return (<div>
             <select className="block py-2.5 px-0 w-full text-sm text-gray-500 bg-transparent border-0 border-b-2 border-gray-200 appearance-none dark:text-gray-400 dark:border-gray-700 focus:outline-none focus:ring-0 focus:border-gray-200 peer" onChange={(event)=>this.props.onFixtureSelect(event.target.value)} >
                <option key="noselectFixture" >Select Fixture</option>
                {
                    this.props.fixtures.map((p)=>(
                        <option key = {p.id} value={p.id} >
                                   {p.homeTeam.name} vs {p.awayTeam.name} @ {p.utcDate}
                        </option>
                    ))
                  }
             </select>
             </div>
    );
    } else {
    return (<div>Select Team to show fixtures </div>);
    }
  }
}

class Game extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            competition: "PL",
            teams: [],
            selectedTeam: null,
            availablePlayers: this.props.players,
            selectedPlayers: [],
            fixtures: [],
            selectedFixture: null,
            selectionComplete: false
        };
    }

     componentDidMount() {
       fetch("/competitions/"+ this.state.competition+"/teams")
          .then(res => res.json())
          .then(
            (result) => {
               console.log(result);
              this.setState({
                isLoaded: true,
                teams: result
              });
            },
            (error) => {
              this.setState({
                isLoaded: true,
                error
              });
            }
          )
      }

    selectFixture = (fixtureId) => {
        //clean up selection
        this.state.selectedPlayers.forEach(p=>  this.removeFromSelectedSquad(p));
        let fixture = this.state.fixtures.find(p=> p.id ===fixtureId);
        fetch("/predictions/fixture/" + fixtureId+ "/team/" + this.state.selectedTeam.id)
            .then((value) => value.json())
            .then(
                (result) => {
                            result.players.forEach(p => this.addToSelectedSquad(p));

                     },
                     (error) => {
                               this.setState({selectedTeam:this.state.selectedTeam,
                                           availablePlayers:this.state.availablePlayers,
                                           selectedPlayers:[],
                                           teams: this.state.teams,
                                           fixtures: this.state.fixtures,
                                           selectedFixture: fixture
                                           });
                     }
                   )
                               ;

    }

    selectTeam = (teamId) => {
        let team = this.state.teams.find(p=> p.id ===teamId);
        if (teamId ==="noselect") {
            this.setState({
                        competition: "PL",
                        teams: this.state.teams,
                        selectedTeam: null,
                        availablePlayers: this.props.players,
                        selectedPlayers: [],
                        fixtures: [],
                        selectedFixture: null,
                        selectionComplete: false
                    });

                return;
        }
        Promise.all([
            fetch("/teams/"+ teamId).then((value) => value.json()),
            fetch("/teams/"+teamId+"/fixtures").then((value) => value.json())]
            )
            .then( ([squad, fixtures]) => {
                this.setState({selectedTeam:team,
                            availablePlayers:squad.squad,
                            selectedPlayers:[],
                            selectedFixture: null,
                            teams: this.state.teams,
                            fixtures: fixtures
                            });


            })

    }

    addToSelectedSquad = (p) => {
        if (!this.state.selectionComplete) {
            if ("GOALKEEPER" === p.position && this.state.selectedPlayers.filter(player => player.position === "GOALKEEPER").length) {
                console.log("Second goalie selected!");
                return;
            } else if ("GOALKEEPER" !== p.position && this.state.selectedPlayers.length ==10 && this.state.selectedPlayers.filter(player => player.position === "GOALKEEPER").length ==0 ) {
               console.log("No goalie selected!");
                return;

            }
            const arr = this.state.availablePlayers.filter(player => player.id !== p.id);
            const joined = this.state.selectedPlayers.concat(p);


            let state = this.state;
            state.availablePlayers = arr;
            state.selectedPlayers = joined;
            this.setState(state);

        }
        let state = this.state;
        state.selectionComplete = this.state.selectedPlayers.length == 11;
        this.setState(state);


    }
    removeFromSelectedSquad = (p) => {

        const arr = this.state.selectedPlayers.filter(player => player.id !== p.id);
        const joined = this.state.availablePlayers.concat(p);

        let state = this.state;
        state.selectionComplete = arr.length == 11;
        state.selectedPlayers = arr;
        state.availablePlayers = joined;
        this.setState(state);
    }
    savePrediction = ()=> {
        console.log(this.state);
          const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({"team": { "id": this.state.selectedTeam.id, "name":this.state.selectedTeam.name }, "fixtureId":this.state.selectedFixture.id, "players": this.state.selectedPlayers})
            };
            fetch('/predictions', requestOptions)
                .then(data => this.setState({
                                                        competition: "PL",
                                                        teams: [],
                                                        selectedTeam: null,
                                                        availablePlayers: this.props.players,
                                                        selectedPlayers: [],
                                                        fixtures: [],
                                                        selectedFixture: null
                                                   }));
    }

        setLineup = ()=> {
            console.log(this.state);
              const requestOptions = {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ "teamId": this.state.selectedTeam.id ,"fixtureId":this.state.selectedFixture.id, "lineup": this.state.selectedPlayers})
                };
                fetch('/lineup', requestOptions)
                   ;
        }
  render() {
    return (
        <div>
            <div className="gap-8 float-right"> <Profile /> </div>
            <div className="columns-1">
                <TeamsList teams={this.state.teams} onTeamSelect={this.selectTeam} />
                <FixturesList fixtures={this.state.fixtures} onFixtureSelect={this.selectFixture} />
            </div>
            <div className ="columns-2">
                <div className=" bg-gradient-to-r from-sky-500 to-indigo-500 ">
                    <PlayerList head="Available Players" players={this.state.availablePlayers} onPlayerClick={this.addToSelectedSquad} />

                </div>
                <div className="flex space-x-4 shadow-2xl bg-white rounded-lg bg-green-600 place-content-center">
                    <StartingSquad  players={this.state.selectedPlayers} onPlayerClick={this.removeFromSelectedSquad} />
                </div>
            </div>
            <button className="mt-6 bg-sky-500 hover:bg-indigo-500 text-white py-2 px-4 rounded-full" onClick={this.savePrediction}>Save Selection</button>

        </div>
    );
  }
}
////hiding <button onClick={this.setLineup}>Set Lineup</button>
const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<Game />);