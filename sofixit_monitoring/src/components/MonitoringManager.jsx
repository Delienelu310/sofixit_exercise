import { useState } from "react";
import Report from "./Report";
import { monitor } from "../api/MonitoringApiService";

export default function MonitoringManager(){
    
    const [isActive, setActive] = useState(true);

    const [time, setTime] = useState(1000);
    const [label, setLabel] = useState("");

    const [reports, setReports] = useState([]);
    const [chosenReport, setChosenReport] = useState(0);

    function launchMonitoring(){
        setActive(false);

        monitor(time)
            .then(response => {
                console.log(response);
                setReports([...reports, 
                    {
                        label: "Service 1: " + label,
                        response: response.data[0]
                    }, 
                    {
                        label: "Service 2: " + label,
                        response: response.data[1]
                    }
                ]);
            })
            .catch(e => console.log(e));
    }

    return (
        <div>
            {/* launching the monitor */}
            <h4 className="m-2">Time:</h4>
            <input disabled={!isActive} type={"number"} className="form-control m-2" value={time} onClick={e => setTime(e.target.value)}/>
            <h4 className="m-2">Label:</h4>
            <input disabled={!isActive} className="form-control m-2" value={label} onClick={e => setLabel(e.target.value)}/>
        
            <button disable={!isActive} onClick={e => launchMonitoring()}>Launch</button>
        
        
            {/* displaying results: */}
            {reports.length != 0 && <div>
                <button onClick={e => setReports([])} className="btn btn-danger">Clear</button>
                
                {reports.map((report, index) => (
                    <button 
                        className={"btn" + (index == chosenReport ? "btn-success" : "")}
                        onClick={e => setChosenReport(index)}
                    >{index + 1}: {report.label}</button>
                ))}    

                {reports
                    .filter((report, index) => index == chosenReport)
                    .map(report => (
                        <Report report={report}/>
                    ))
                }


            </div>}
        </div>
    );
}