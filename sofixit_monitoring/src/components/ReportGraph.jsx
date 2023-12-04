
export function ReportGraph({graph, chosenGraph, label, start, end}){

    return (
        <div>
            <VictoryChart width={400} height={300} domain={{ x: domainX }}>

                <VictoryScatter
                    data={graph}
                    size={3}
                    labels={({ datum }) => ` ${datum.y}`} 

                />

                <VictoryScatter
                    data={chosenGraph}
                    size={5}
                    labels={({ datum }) => ` ${datum.y}`} 
                    style={{
                        data: { fill: 'red'},
                    }}

                />


                <VictoryAxis label="Time (ms)"
                    tickFormat={(date) => new Date(date).getTime() - new Date(start).getTime()}
                    scale="time"
                />
                <VictoryAxis dependentAxis 
                    label={label}

                />
            </VictoryChart>
        </div>
    );
}