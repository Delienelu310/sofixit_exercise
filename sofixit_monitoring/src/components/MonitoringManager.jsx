import { useState } from "react";
import Report from "./Report";
import { monitor } from "../api/MonitoringApiService";


export default function MonitoringManager(){
    
    const [isActive, setActive] = useState(true);

    const [time, setTime] = useState(1000);
    const [label, setLabel] = useState("");

    const [reports, setReports] = useState([]);
    const [chosenReport, setChosenReport] = useState(null);

    function launchMonitoring(){
        setActive(false);

        monitor(time)
            .then(response => {
                setReports([...reports, 
                    {
                        label: "Service 1: " + label,
                        report: response.data[0]
                    }, 
                    {
                        label: "Service 2: " + label,
                        report: response.data[1]
                    }
                ]);
                setActive(true);
            })
            .catch(e => console.log(e));
    }

    return (
        <div style={{
            margin: "auto",
            width: "75%"
        }}>
            {/* launching the monitor */}
            <h4 className="m-2">Time:</h4>
            <input disabled={!isActive} type={"number"} className="form-control m-2" value={time} onChange={e => setTime(e.target.value)}/>
            <h4 className="m-2">Label:</h4>
            <input disabled={!isActive} className="form-control m-2" value={label} onChange={e => setLabel(e.target.value)}/>
        
            <button disabled={!isActive} onClick={e => launchMonitoring()}>Launch</button>
        
        
            {/* displaying results: */}
            {reports.length != 0 && <div>
                <button onClick={e => setReports([])} className="btn btn-danger">Clear</button>
                
                {reports.map((report, index) => (
                    <button 
                        className={"m-2 btn"}
                        onClick={e => setChosenReport(report)}
                    >{index + 1}: {report.label}</button>
                ))}    

                {chosenReport && 
                    <div>
                        <h4 className="m-2">Chosen: {chosenReport.label}</h4>
                        <Report report={chosenReport}/>
                    </div>
                }
                 
            </div>}
        </div>
    );
}