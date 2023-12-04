import RequestManager from "./components/RequestsManager";

function App() {
  return (
    <div className="App">
      <RequestManager urls={[
        {url: "http://localhost:4000/test", label: "test"}
      ]}/>
    </div>
  );
}

export default App;
