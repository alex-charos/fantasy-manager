class LeagueCreation extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            name: null,
            code: null,
            participants: []

        };
    }

    updateLeagueName = (name) => {
        this.setState({
                    name: name,
                    code: this.state.code,
                    participants: this.state.participants
        });
    }
        updateLeagueCode = (code) => {
            this.setState({
                        name: this.state.name,
                        code: code,
                        participants: this.state.participants
            });
        }


    saveLeague = ()=> {
     const requestOptions = {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({"name": this.state.name})
                };
                fetch('/leagues', requestOptions)
                    .then(data => this.setState({
                                                    name: data.name,
                                                    code: data.code,
                                                    participants: data.participants
                                                }));


    }
    joinLeague = ()=> {
         const requestOptions = {
                        method: 'PUT',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({"name": this.state.name})
                    };
                    fetch('/leagues/'+this.state.code, requestOptions)
                        .then(data => this.setState({
                                                        name: data.name,
                                                        code: data.code,
                                                        participants: data.participants
                                                    }));


        }
    render() {
        return (<div>
                    <div>League Name: <input  onChange={evt => this.updateLeagueName(evt.target.value)}/>
                            <button onClick={this.saveLeague}> Save!</button></div>

                    <div>League Code: <input  onChange={evt => this.updateLeagueCode(evt.target.value)}/>
                                                <button onClick={this.joinLeague}> Join!</button></div>
                </div>
        );
        }
}




const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<LeagueCreation />

);