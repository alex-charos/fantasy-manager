class Square extends React.Component {
  render() {
    return (
      <button className="square">
        {this.props.value}
      </button>
    );
  }
}
class PlayerList extends React.Component {

    render() {
        if (this.props.players) {
        return (

            <table>
                <thead>
                <tr>
                    <th> ID {this.props.head} </th>
                    <th> Name </th>
                    <th> Position </th>
                </tr>
                </thead>
                <tbody>
                {
                    this.props.players.map((p)=>(
                        <tr key = {p.id} onClick={()=>this.props.onPlayerClick(p)} >
                                    <td>{p.id}</td>
                                    <td>{p.name}</td>
                                    <td>{p.position}</td>
                                </tr>
                    ))
                  }
                  </tbody>
            </table>
        );
    } else {
        return (<div>Please select Team to show players</div>);
    }
    }
}

class Player extends React.Component {

    render() {
        return (<div class = {this.props.position}>
                {this.props.name}

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
    return (
             <select>
                <option key="noselect">Select Team</option>
                {
                    this.props.teams.map((p)=>(
                        <option key = {p.id} onClick={()=>this.props.onTeamSelect(p)} >
                                   {p.name}
                        </option>
                    ))
                  }
             </select>
    );
  }
}
class FixturesList extends React.Component {
  render() {
   if (this.props.fixtures) {
    return (
             <select>
                <option key="noselectFixture">Select Fixture</option>
                {
                    this.props.fixtures.map((p)=>(
                        <option key = {p.id} onClick={()=>this.props.onFixtureSelect(p)} >
                                   {p.homeTeam.name} vs {p.awayTeam.name} @ {p.utcDate}
                        </option>
                    ))
                  }
             </select>
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
            selectedFixture: null
        };
    }

     componentDidMount() {
       fetch("/competitions/"+ this.state.competition+"/teams")
          .then(res => res.json())
          .then(
            (result) => {
              this.setState({
                isLoaded: true,
                teams: result.teams
              });
            },
            // Note: it's important to handle errors here
            // instead of a catch() block so that we don't swallow
            // exceptions from actual bugs in components.
            (error) => {
              this.setState({
                isLoaded: true,
                error
              });
            }
          )
      }

    selectFixture = (fixture) => {
        this.setState({selectedTeam:this.state.selectedTeam,
                    availablePlayers:this.state.availablePlayers,
                    selectedPlayers:this.state.selectedPlayers,
                    teams: this.state.teams,
                    fixtures: this.state.fixtures,
                    selectedFixture: fixture
                    });
    }

    selectTeam = (team) => {
        Promise.all([
            fetch("/teams/"+ team.id).then((value) => value.json()),
            fetch("/teams/"+team.id+"/fixtures").then((value) => value.json())]
            )
            .then( ([squad, fixtures]) => {
                this.setState({selectedTeam:team,
                            availablePlayers:squad.squad,
                            selectedPlayers:[],
                            selectedFixture: null,
                            teams: this.state.teams,
                            fixtures: fixtures.matches
                            });


            })


        fetch("/teams/"+ team.id)
                  .then(res => res.json())
                  .then(
                    (result) => {
                              this.setState(
                                {   selectedTeam:team,
                                    selectedPlayers:result.squad,
                                    availablePlayers:[],
                                    selectedFixture: null,
                                    teams: this.state.teams,
                                    fixtures:[]
                                    }
                                    );

                    },
                    // Note: it's important to handle errors here
                    // instead of a catch() block so that we don't swallow
                    // exceptions from actual bugs in components.
                    (error) => {
                      this.setState({
                        isLoaded: true,
                        error
                      });
                    }
                  )
    }

    addToSelectedSquad = (p) => {
        console.log(this.state);
        const arr = this.state.availablePlayers.filter(player => player !== p);
        const joined = this.state.selectedPlayers.concat(p);

        this.setState({availablePlayers:arr, selectedPlayers:joined});
    }
    removeFromSelectedSquad = (p) => {
        console.log(this.state);
        const arr = this.state.selectedPlayers.filter(player => player !== p);
        const joined = this.state.availablePlayers.concat(p);

        this.setState({availablePlayers:joined, selectedPlayers:arr});
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
            <TeamsList teams={this.state.teams} onTeamSelect={this.selectTeam} />
            <FixturesList fixtures={this.state.fixtures} onFixtureSelect={this.selectFixture} />
            <PlayerList head="Available Players" players={this.state.availablePlayers} onPlayerClick={this.addToSelectedSquad} />
            <PlayerList head="Selected Players" players={this.state.selectedPlayers} onPlayerClick={this.removeFromSelectedSquad} />
            <button onClick={this.savePrediction}>Save Selection</button>
            <button onClick={this.setLineup}>Set Lineup</button>
        </div>
    );
  }
}

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<Game />);
//<PlayerList head="Total Squad" players= {this.state.availablePlayers} onPlayerClick={this.addToSelectedSquad}  />
          //    <PlayerList head="Selected"  players= {this.state.selectedPlayers} onPlayerClick={this.removeFromSelectedSquad}  />
