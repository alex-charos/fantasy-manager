const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
    <div>
        <div> <Profile /> </div>
        <div><Game isAdmin={false} /> </div>
    </div>
);