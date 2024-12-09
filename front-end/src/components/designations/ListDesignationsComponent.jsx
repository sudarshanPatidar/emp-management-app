import { useEffect, useState } from "react"
import {useNavigate} from 'react-router-dom'
import { deleteDesignationApi, retrieveAllDesignations } from "./api/DesignationApiService"
// import { useAuth } from "./security/AuthContext"

function ListDesignationsComponent() {


    // const authContext = useAuth()

    // const username = authContext.username

    const navigate = useNavigate()
    

    const [designations,setDesignations] = useState([])

    const [message,setMessage] = useState(null)
    
    useEffect ( () => refreshDesignations(), [])

    function refreshDesignations() {
        
        retrieveAllDesignations()
        .then(response => {
            setDesignations(response.data)
        }
            
        )
        .catch(error => console.log(error))
    
    }

    // function deleteDesignation(code) {
    //     // console.log('clicked ' + code)
    //     deleteDesignationApi(code)
    //     .then(

    //         () => {
    //             setMessage(`Delete of designation with id = ${code} successful`)
    //             refreshDesignations()
    //         }
    //         //1: Display message
    //         //2: Update Todos list
    //     )
    //     .catch(error => console.log(error))
    // }



    function deleteDesignation(code) {
        deleteDesignationApi(code)
            .then(() => {
                setMessage(`Delete of designation with id = ${code} successful`);
                refreshDesignations();
            })
            .catch(error => {
                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    setMessage(`Error: ${error.response.data}`);
                } else if (error.request) {
                    // The request was made but no response was received
                    setMessage('Error: No response received from the server');
                } else {
                    // Something happened in setting up the request that triggered an Error
                    setMessage(`Error: ${error.message}`);
                }
                // console.log(error);
            });
    }
    

    function updateDesignation(code) {
        console.log('clicked ' + code)
        navigate(`/designations/${code}`)
    }

    function addNewDesignation() {
        navigate(`/designations/-1`)
    }

    return (
        <div className="container">
            
            {message && <div className="alert alert-warning">{message}</div>}

            
            <div>
                <table className="table">
                    <thead>
                            <tr>
                                <th>Code</th>
                                <th>Title</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                    </thead>
                    <tbody>
                    {
                        designations.map(
                            designation => (
                                <tr key={designation.code}>
                                    <td>{designation.code}</td>
                                    <td>{designation.title}</td>
                                    <td> <button className="btn btn-warning" 
                                                    onClick={() => deleteDesignation(designation.code)}>Delete</button> </td>
                                    <td> <button className="btn btn-success" 
                                                    onClick={() => updateDesignation(designation.code)}>Update</button> </td>
                                </tr>
                            )
                        )
                    }
                    </tbody>

                </table>
            </div>
            <div className="btn btn-success m-5" onClick={addNewDesignation}>Add New Designation</div>
        </div>
    )
}

export default ListDesignationsComponent