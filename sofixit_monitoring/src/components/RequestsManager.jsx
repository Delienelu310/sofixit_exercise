import { useState } from "react";

export default function RequestManager({urls}){

    const [size, setSize] = useState(100);
    const [format, setFormat] = useState("");

    const [requests, setRequests] = useState([]);

    const [responses, setResponses] = useState([]);
    const [chosenResopnse, setChosenResponse] = useState(0);

    const [active, setActive] = useState(true);

    function sendRequests(){
        setActive(false);

        for(let request of requests){

        }

        setActive(true);
    }

    return (
        <div>

            {/* options of requests to choose */}
            <div>
                {urls.map((url, index) => (
                    <button
                        key={index}
                        className="btn btn-success m-2"
                        onClick={e => {
                            setRequests([...requests, {
                                label: url.label,
                                url: url.url,
                                format: format,
                                size: size
                            }])
                        }}
                    >
                        {url.label}
                    </button>
                ))}              
            </div>

            {/* inputs */}
            <div>Size:</div>
            <input disabled={!active} className="m-2 form-control" value={size} onChange={e => setSize(e.target.value)}/>
            <div>Format</div>
            <input disabled={!active} className="m-2 form-control" value={format} onChange={e => setFormat(e.target.value)}/>

            {/* Requests list */}
            <div className="m-3">
                {requests.map((request, index) => (
                    <div className="m-3" key={index}>
                        <h3>{request.label}</h3>
                        Size: {request.size}, Format: {request.format}
                        <button 
                            className="btn btn-danger m-2"
                            onClick={e => {
                                setRequests(requests.filter(req => req != request));
                            }}
                        >Delete</button>
                    </div>
                ))}
            </div>

            {/* BUtton to perform the requests */}
            <button 
                className="m-5 btn btn-success"
                onClick={e => {
                    sendRequests();
                }}  
            >Send</button>

            {/* list o responses */}
            {responses.length != 0 && <div className="m-5">
                <button onClick={e => {
                    setResponses([]);
                }}>Clear</button>

                {responses.map((response, index) => {
                    <button className="btn m-1" onClick={e => {
                        setChosenResponse(index);
                    }}>{index+1}</button>
                })}

                {responses
                    .filter((response, index) => index == chosenResopnse)
                    .map( (response, index) => {
                        <div className="m-3">
                            <h3>{response.request.label}</h3>
                            Size: {response.request.size}, Format: {response.request.format}
                            <hr></hr>
                            <div>{response.response.responseBody}</div>
                        </div>
                    })
                }

                
            </div>}

        </div>
    );
}