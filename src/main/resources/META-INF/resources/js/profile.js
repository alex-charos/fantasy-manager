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



    render() {
        if (this.state.isLoaded) {
        return (
                <div>
                Hello, {this.state.user.name} !
                </div>
        );
        } else {
            return (<div>Loading...</div>);
        }
    }
}