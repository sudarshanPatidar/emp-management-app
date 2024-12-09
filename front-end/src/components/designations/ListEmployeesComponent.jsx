import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { retrieveAllEmployees , deleteEmployeeApi} from "./api/EmployeeApiService"; // Assuming you have these API service functions

function ListEmployeesComponent() {
    const navigate = useNavigate();
    const [employees, setEmployees] = useState([]);
    const [message, setMessage] = useState(null);

    useEffect(() => refreshEmployees(), []);
    
    
    function refreshEmployees() {
        retrieveAllEmployees()
            .then(response => {
                console.log(response.data); // Log the response data to verify the structure
                const formattedEmployees = response.data.map(employee => ({
                    ...employee,
                    dateOfBirth: formatDate(employee.dateOfBirth) // Format dateOfBirth
                }));
                setEmployees(formattedEmployees);
            })
            .catch(error => console.log(error));
    }
    

    function formatDate(dateString) {
        const date = new Date(dateString);
        return date.toLocaleDateString();
    }

    function deleteEmployee(employeeId) {
        deleteEmployeeApi(employeeId)
            .then(() => {
                setMessage(`Delete of employee with id = ${employeeId} successful`);
                setTimeout(() => {
                    refreshEmployees();
                }, 500);
            })
            .catch(error => console.log(error));
    }

    function updateEmployee(employeeId) {
        navigate(`/employees/${employeeId}`);
    }

    function addNewEmployee() {
        navigate(`/employees/A00000000`);
    }


    return (
        <div className="container">
            {message && <div className="alert alert-warning">{message}</div>}
            <div>
                <table className="table">
                    <thead>
                        <tr>
                            <th>Employee ID</th>
                            <th>Name</th>
                            <th>Designation Code</th>
                            <th>Gender</th>
                            <th>Basic Salary</th>
                            <th>Is Indian</th>
                            <th>PAN Number</th>
                            <th>Aadhar Number</th>
                            <th>Date of Birth</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        {employees.map(employee => (
                            <tr key={employee.employeeId}>
                                <td>{employee.employeeId}</td>
                                <td>{employee.name}</td>
                                <td>{employee.designationCode}</td>
                                <td>{employee.gender}</td>
                                <td>{employee.basicSalary}</td>
                                <td>{employee.isIndian ? "Yes" : "No"}</td>
                                <td>{employee.pannumber}</td>
                                <td>{employee.aadharCardNumber}</td>
                                <td>{employee.dateOfBirth}</td>
                                <td>
                                    <button className="btn btn-warning" onClick={() => deleteEmployee(employee.employeeId)}>Delete</button>
                                </td>
                                <td>
                                    <button className="btn btn-success" onClick={() => updateEmployee(employee.employeeId)}>Update</button>
                                </td> 
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <div className="btn btn-success m-5" onClick={addNewEmployee}>Add New Employee</div>
        </div>
    );
}

export default ListEmployeesComponent;
