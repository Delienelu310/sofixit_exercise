import { useState } from "react";
import Report from "./Report";

export default function MonitoringManager(){
    
    const [isActive, setActive] = useState(true);

    const [time, setTime] = useState(0);

    const [reports, setReports] = useState([]);
    const [chosenReport, setChosenReport] = useState(0);

    function launchMonitoring(){
        setActive(false);

        // some promises and work with apis
    }

    return (
        <div>
            {/* launching the monitor */}
            <h4>Time:</h4>
            <input disabled={!isActive} className="form-control m-2" value={time} onClick={e => setTime(e.target.value)}/>
        
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
                    .filter((report, index) => index = chosenReport)
                    .map(report => (
                        <Report report={report}/>
                    ))
                }


            </div>}
        </div>
    );
}