import { VictoryChart,VictoryScatter, VictoryLine, VictoryTooltip } from 'victory';

export function ReportGraph({graph, chosenGraph, label, start, end}){

    return (
        <div>
            <VictoryChart width={400} height={300}>

                <VictoryScatter
                    data={graph}
                    size={2}
                    style={{
                        data: { fill: 'blue'},
                    }}
                    labels={({ datum }) => datum.y}
                    labelComponent={
                        <VictoryTooltip
                            flyoutStyle={{ fill: 'white' }}
                        />
                    }

                />

                <VictoryLine
                    data={graph}
                    style={{
                        data: { stroke: 'blue' }
                    }}
                />

                <VictoryScatter
                    data={chosenGraph}
                    size={5}
                    style={{
                        data: { fill: 'red'},
                    }}

                />

            </VictoryChart>
        </div>
    );
}