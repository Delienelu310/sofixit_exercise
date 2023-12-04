import { useEffect, useState } from "react";
import { ReportGraph } from "./ReportGraph";
import { FunctionCallsList } from "./FunctionCallsList";

export default function Report({report}){

    const [memoryGraph, setMemoryGraph] = useState([]);
    const [cpuUsageGraph, setCpuUsageGraph] = useState([]);
    const [chosenPointsMemory, setChosenPointsMemory] = useState([]);
    const [chosenPointsCpu, setChosenPointsCpu] = useState([]);

    const [functionCalls , setFunctionCalls] = useState([]);

    function convertData(){
        setFunctionCalls(report.report.calls);
    
        let memoryData = [];
        let cpuData =[];
        for(let point of report.report.dataGraph){
            let timeMS = new Date(point.dateTime).getTime() - new Date(report.report.start).getTime();
            
            memoryData.push({
                "x": timeMS,
                "y": point.memoryUsed / 1024 / 1024
            });

            cpuData.push({
                "x": timeMS,
                "y": point.cpuUsage
            });
        }

        setMemoryGraph(memoryData);
        setCpuUsageGraph(cpuData);
    }

    useEffect(() => {
        convertData();
    }, [report]);

    return (
        <div>
            {/* Label and time range of the report */}
            <h3>{report.label}</h3>
            Started: {report.report.start}
            <br/>
            Ended: {report.report.end}
            
            {/* Graphs, that describe memory usage and cpu usage, where separeate function calls are highlighted with its own color */}
            <div>
                <h3>Memory usage:</h3>
                <ReportGraph
                    chosenGraph={chosenPointsMemory}
                    graph={memoryGraph}
                    start={report.report.start}
                    end={report.report.end}
                    label={"Bytes"}
                />

                <FunctionCallsList
                    setChosenPoints={setChosenPointsMemory}
                    graph={memoryGraph}
                    start={report.report.start}
                    end={report.report.end}
                    calls={functionCalls}
                />

                <h3>CPU usage</h3>
                <ReportGraph
                    chosenGraph={chosenPointsCpu}
                    graph={cpuUsageGraph}
                    start={report.report.start}
                    end={report.report.end}
                    label={"CPU"}
                />

                <FunctionCallsList
                    setChosenPoints={setChosenPointsCpu}
                    graph={cpuUsageGraph}
                    start={report.report.start}
                    end={report.report.end}
                    calls={functionCalls}
                />
            </div>
        </div>
    );
}