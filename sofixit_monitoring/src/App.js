import RequestManager from "./components/RequestsManager";

function App() {
  return (
    <div className="App">
      <RequestManager urls={[
        {url: "http://localhost:4000/request/1/", label: "Only CSV"},
        {url: "http://localhost:4000/request/2/", label: "CSV in format"},
        {url: "http://localhost:4000/request/3/", label: "CSV + math"}
      ]}/>
    </div>
  );
}

export default App;
