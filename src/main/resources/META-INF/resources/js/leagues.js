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

                        setName("");
                        setCode(data.code);
                        setLeagueCreated(true);
                        props.leagueCreated(data);
                    });
    }
    return (<div>
                    <input placeholder="League Name" value={name} className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block  p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"  onChange={evt => setName(evt.target.value)}/>
                    <button className="mt-6 bg-sky-500 hover:bg-indigo-500 text-white py-2 px-4 rounded-full" onClick={createLeague}> Create League!</button>
                    { leagueCreated ?
                            <div>
                                <div>League Created! Ask your friends to join with code: </div>
                                <div>{code}</div>
                            </div>
                        : null }
                </div>
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
                   <input placeholder="Code" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block  p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"  onChange={evt => setCode(evt.target.value)}/>
                   <button className="mt-6 bg-sky-500 hover:bg-indigo-500 text-white py-2 px-4 rounded-full" onClick={joinLeague}> Join!</button>
                   { leagueJoined ?
                        <div>
                            <div>You just joined league with name: </div>
                            <div>{name}</div>
                        </div>
                        : null }
                </div>
        );

}

const MyLeagues = (props) => {
    return  (   <div>
                { props.leagues.length >0 ?
                    <div>
                        <div>My Leagues</div>
                            <table className="table-auto w-full text-sm text-left text-gray-500 dark:text-gray-400">
                                <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                                    <tr>
                                        <td>Name</td>
                                        <td>Players</td>
                                        <td>-</td>
                                    </tr>
                                </thead>

                                <tbody>
                                    { props.leagues.map((p,i)=>
                                        <LeagueRow key={i} league={p} onLeagueSelect={props.onLeagueSelect}  onLeagueLeave={props.onLeagueLeave} />
                                    )}
                                </tbody>
                            </table>
                    </div>
                    : <div> Not participating in any leagues </div>
                    }
                    </div>

    );

}

const LeagueRow = (props) => {
    return (
            <tr  className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                <td onClick={()=>props.onLeagueSelect(props.league) }>{props.league.name? props.league.name: "N/A"}</td>
                <td>{props.league.participants.length}</td>
                <td onClick={()=>props.onLeagueLeave(props.league) }>Leave</td>
            </tr>

    );

}

const League = (props) => {
    return (
        props.league ?
        <div>
            <label>League Name:</label>
            <div>{props.league.name}</div>
            <div>
                <label>Participants</label>
                <table className="table-auto w-full text-sm text-left text-gray-500 dark:text-gray-400">
                    <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                        <tr>
                            <td>#</td>
                            <td>Player</td>
                            <td>Points</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                           props.league.participants.map((p,i) =>
                             <tr className="bg-white border-b dark:bg-gray-800 dark:border-gray-700" key={i}>
                                <td>{i+1}</td>
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



const LeagueManagement  = () =>{
    const [creatingLeague, setCreatingLeague] = React.useState(true);
    const [leagues, setLeagues] = React.useState([]);
    const [selectedLeague, setSelectedLeague] = React.useState();

    React.useEffect(() => {
        getLeagues();
    }, []);

    const getLeagues = ()=> {
            fetch('/leagues')
                            .then((value) => value.json())
                            .then(data => {
                                    setLeagues(data);
                            });
    }

    const leagueCreated = (league) => {
        setSelectedLeague(league);
        getLeagues();
    }
    const leagueJoined = (league) => {
        setSelectedLeague(league);
        getLeagues();
    }

    const leaveLeague =  (league) => {
         const requestOptions = {
                        method: 'DELETE',
                        headers: { 'Content-Type': 'application/json' }
                    };
                    fetch('/leagues/'+league.code, requestOptions)
                        .then(data =>  {
                                        getLeagues();
                                        if (league.code === selectedLeague.code) {
                                            setSelectedLeague(null);
                                        }
                                        });
    }

    return ( <div>
                 <div> <Profile /> </div>
                 <div  className="grid grid-rows-1 grid-cols-3 gap-4">
                <div>
                    <div className="columns-2 w-96">
                        <div className="basis-1/2  divide-y-2 space-x-4">
                        <button className="bg-gradient-to-r from-sky-500 to-indigo-500" onClick={()=>setCreatingLeague(false)}>Join A league </button>
                        </div>
                        <div className="basis-1/2  divide-y-2 space-x-4">
                        <button className="bg-gradient-to-r from-indigo-500 to-sky-500" onClick={()=>setCreatingLeague(true)}>Create A league</button>
                        </div>
                    </div>
                    <div className="columns-1 w-96">
                        {
                        creatingLeague ?
                            <div className="bg-gradient-to-r from-indigo-500 to-sky-500 ">
                                <LeagueCreation  leagueCreated={leagueCreated} />
                            </div>
                        :
                            <div className="bg-gradient-to-r from-sky-500 to-indigo-500 ">
                                <LeagueParticipation leagueJoined={leagueJoined} />
                            </div>
                    }
                    </div>
                </div>
                 <div className="columns-1 w-96">
                    <MyLeagues leagues={leagues} onLeagueSelect={setSelectedLeague} onLeagueLeave={leaveLeague}/>
                </div>
                <div>
                    <League league={selectedLeague} />
                </div>
                </div>
            </div>
    );
}

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<LeagueManagement />

);