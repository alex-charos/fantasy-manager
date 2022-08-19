const LeagueCreation = (props) => {

    const [leagueCreated, setLeagueCreated] = React.useState(false);
    const [name, setName] = React.useState("")
    const [code, setCode] = React.useState("")


   const createLeague = ()=> {

     const requestOptions = {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({"name": name})
                };
                fetch('/leagues', requestOptions)
                    .then((value) => value.json())
                    .then(data => {

                        setName(data.name);
                        setCode(data.code);
                        setLeagueCreated(true);
                        props.leagueCreated(data);
                    });
    }
    return (<div>
                    <label>League Name</label>
                    <input  onChange={evt => setName(evt.target.value)}/>
                    <button onClick={createLeague}> Create League!</button>
                    { leagueCreated ?
                            <div>
                                <div>League Created! Ask your friends to join with code: </div>
                                <div>{code}</div>
                            </div>
                        : null }
                </div>
        );

}

const MyLeagues = (props) => {
    return  ( <div>
            { props.leagues.map((p,i)=>(
                    <LeagueRow key={i+1} league={p} onLeagueSelect={props.onLeagueSelect}  />
                    ))
            }
            </div>
    );

}

const LeagueRow = (props) => {
    return (
            <div onClick={()=>props.onLeagueSelect(props.league) }>
                <div>{props.league.name}</div>
                <div>{props.league.participants.length}</div>
                <div>{props.league.code}</div>
            </div>

    );

}

const League = (props) => {
    return (
        props.league ?
        <div>
            <label>League Name:</label>
            <div>props.league.name</div>
            <div>
                <label>Participants</label>
                <table>
                    <thead>
                        <tr>
                            <td>#</td>
                            <td>Player</td>
                            <td>Points</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                           props.league.participants.map((p,i) =>
                             <tr key={i}>
                                <td>{i}</td>
                                <td>{p.predictor.username}</td>
                                <td>{p.points}</td>
                             </tr>
                           )
                        }
                    </tbody>
                </table>
            </div>
        </div>
        :
        <div> Select A league to view it! </div>
    );
}

const LeagueParticipation = () => {

    const [leagueJoined, setLeagueJoined] = React.useState(false);
    const [code, setCode] = React.useState("")
    const [name, setName] = React.useState("")

    const  joinLeague = ()=> {
         const requestOptions = {
                        method: 'PUT',
                        headers: { 'Content-Type': 'application/json' }
                    };
                    fetch('/leagues/'+code, requestOptions)
                        .then((value) => value.json())
                        .then(data => {
                                setName(data.name);
                                setLeagueJoined(true);
                        });
    }

        return (<div>
                   <label>League Code:</label>
                   <input  onChange={evt => setCode(evt.target.value)}/>
                   <button onClick={joinLeague}> Join!</button>
                   { leagueJoined ?
                        <div>
                            <div>You just joined league with name: </div>
                            <div>{name}</div>
                        </div>
                        : null }
                </div>
        );

}

const LeagueManagement  = () =>{
    const [creatingLeague, setCreatingLeague] = React.useState(true);
    const [leagues, setLeagues] = React.useState([]);
    const [selectedLeague, setSelectedLeague] = React.useState();

    React.useEffect(() => {
        fetch('/leagues')
                        .then((value) => value.json())
                        .then(data => {
                                setLeagues(data);
                        });
    }, []);

    const leagueCreated = (league) => {
        console.log(league);
        setSelectedLeague(league);
    }
    const leagueJoined = (league) => {
        setSelectedLeague(league);
    }

    const test =  (league) => {
        console.log(league);
        setSelectedLeague(league);
    }

    return ( <div>
                <MyLeagues leagues={leagues} onLeagueSelect={test} />
                {
                    creatingLeague ?
                        <div>
                            <button onClick={()=>setCreatingLeague(false)}>Join A league instead!</button>
                            <LeagueCreation  leagueCreated={leagueCreated} />
                        </div>
                    :
                        <div>
                            <button onClick={()=>setCreatingLeague(true)}>Create A league...</button>
                            <LeagueParticipation leagueJoined={leagueJoined} />
                        </div>

                }
                <League league={selectedLeague} />
            </div>
    );


}




const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<LeagueManagement />

);