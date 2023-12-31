import RequestManager from "./components/RequestsManager";
import MonitoringManager from "./components/MonitoringManager";
import './App.css'

function App() {
  return (
    <div className="App">
      <RequestManager urls={[
        {url: "http://localhost:4000/request/0/", label: "Only CSV"},
        {url: "http://localhost:4000/request/1/", label: "CSV in format"},
        {url: "http://localhost:4000/request/2/", label: "CSV + math"}
      ]}/>

      <MonitoringManager/>
    </div>
  );
}

export default App;
