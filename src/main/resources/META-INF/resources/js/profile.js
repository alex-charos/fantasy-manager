class Profile extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            isLoaded: false
        };
    }


 componentDidMount() {
       fetch("/users/me")
          .then(res => res.json())
          .then(
            (result) => {
              this.setState({
                isLoaded: true,
                user: result
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



    render() {
        if (this.state.isLoaded) {
        return (
                <div className="gap-8 float-right flex columns-1">
                    <div>
                        Hello, {this.state.user.name} !
                    </div>
                    <div>
                        <a className="mt-6 bg-sky-500 hover:bg-indigo-500 text-white py-2 px-4 rounded-full" href="/">Game</a>
                    </div>
                    <div>
                        <a className="mt-6 bg-sky-500 hover:bg-indigo-500 text-white py-2 px-4 rounded-full" href="/leagues.html">Leagues</a>
                    </div>
                </div>
        );
        } else {
            return (<div>Loading...</div>);
        }
    }
}