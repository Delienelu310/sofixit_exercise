

export function FunctionCallsList({graph, calls, start, end, setChosenPoints}){

    return (
        <div>
            {calls
                .filter(functionCall => 0 <= (new Date(functionCall.start) - new Date(start))
                ).map((functionCall, index) => (
                    <button
                        className="btn"
                        onClick={e => {
                            setChosenPoints(graph.filter(
                                point => {
                                    return new Date(point.x).getTime() <= (new Date(functionCall.end) - new Date(start)) && 
                                    new Date(point.x).getTime() >= (new Date(functionCall.start) - new Date(start))
                                }
                            ));
                        }}
                    >
                        {index + 1}. 
                        {(new Date(functionCall.start)- new Date(start))}ms 
                        - 
                        { (new Date(functionCall.end) - new Date(start))}ms
                    </button>
            ))}
        </div>
    );
}