

export function FunctionCallsList({graph, calls, start, end, setChosenPoints}){

    return (
        <div>
            {calls.map((functionCall, index) => (
                <button
                    className="btn"
                    onClick={e => {
                        setChosenPoints(graph.filter(
                            point => point.x <= new Date(functionCall.end).getTime() - new Date(start).getTime()
                                && point.x >= new Date(functionCall.start).getTime() - new Date(start).getTime()
                        ));
                    }}
                >
                    {index + 1}. {functionCall.start -start}ms - {functionCall.end - start}ms
                </button>
            ))}
        </div>
    );
}