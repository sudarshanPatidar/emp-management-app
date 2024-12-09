import { retrieveEmployeeByEmployeeIdApi, updateEmployeeApi, createEmployeeApi } from './api/EmployeeApiService';
import { useEffect, useState, useCallback } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
// import { retrieveEmployeeByIdApi, updateEmployeeApi, createEmployeeApi } from './api/EmployeeApiService';
import { Formik, Form, Field } from 'formik';

function formatDate(dateString) {
    if (!dateString) return '';
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

export default function EmployeeComponent() {
    const { employeeId } = useParams();
    const [employee, setEmployee] = useState({
        name: '',
        designationCode: '',
        dateOfBirth: '',
        isIndian: '',
        gender: '',
        basicSalary: '',
        pannumber: '',
        aadharCardNumber: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const retrieveEmployee = useCallback(() => {
        if (employeeId !== 'A00000000') {
            retrieveEmployeeByEmployeeIdApi(employeeId)
                .then(response => {
                    const data = response.data;
                    data.dateOfBirth = formatDate(data.dateOfBirth);
                    setEmployee(data);
                })
                .catch(error => console.error(error));
        }
    }, [employeeId]);

    useEffect(() => {
        retrieveEmployee();
    }, [retrieveEmployee]);

    function onSubmit(values) {
        const employeeData = {
            employeeId: employeeId,
            name: values.name,
            designationCode: values.designationCode,
            dateOfBirth: new Date(values.dateOfBirth).toISOString(),
            isIndian: values.isIndian === 'true',
            gender: values.gender,
            basicSalary: values.basicSalary,
            pannumber: values.pannumber,
            aadharCardNumber: values.aadharCardNumber
        };

        if (employeeId === 'A00000000') {
            const newEmployeeData = {
                name: values.name,
                designationCode: values.designationCode,
                dateOfBirth: new Date(values.dateOfBirth).toISOString(),
                isIndian: values.isIndian === 'true',
                gender: values.gender,
                basicSalary: values.basicSalary,
                pannumber: values.pannumber,
                aadharCardNumber: values.aadharCardNumber
            };
            createEmployeeApi(newEmployeeData)
                .then(response => {
                    navigate('/employees');
                })
                .catch(error => {
                    if (error.response && error.response.status === 409) {
                        setError(`Conflict Error: ${error.response.data}`);
                    } else {
                        setError('Error creating employee');
                    }
                    console.error('Error creating employee:', error);
                });
        } 
        else {
            updateEmployeeApi(employeeData)
                .then(response => {
                    if (response.status !== 200) {
                        if (response.status === 409) {
                            setError(`Conflict Error: ${response.data}`);
                        } else {
                            setError(`Error: ${response.data}`);
                        }
                    } else {
                        navigate('/employees');
                    }
                    console.log(response)
                })
                .catch(error => {
                    if (error.response && error.response.status === 409) {
                        setError(`Conflict Error: ${error.response.data}`);
                    } else {
                        setError('Error updating employee');
                    }
                });
        }
    }

    return (
        <div className="container">
            <h1>Enter Employee Details</h1>
            <div>
                {error && <div className="alert alert-warning m-3">{error}</div>}
                <Formik initialValues={employee} enableReinitialize={true} onSubmit={onSubmit}>
                    {(props) => (
                        <Form>
                            <fieldset className="form-group">
                                <label>Name</label>
                                <Field type="text" className="form-control" name="name" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Designation Code</label>
                                <Field type="number" className="form-control" name="designationCode" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Date of Birth</label>
                                <Field type="date" className="form-control" name="dateOfBirth" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Is Indian</label>
                                <Field as="select" className="form-control" name="isIndian">
                                    <option value="">Select</option>
                                    <option value="true">Yes</option>
                                    <option value="false">No</option>
                                </Field>
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Gender</label>
                                <Field as="select" className="form-control" name="gender">
                                    <option value="">Select</option>
                                    <option value="M">Male</option>
                                    <option value="F">Female</option>
                                </Field>
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Basic Salary</label>
                                <Field type="number" className="form-control" name="basicSalary" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>PAN Number</label>
                                <Field type="text" className="form-control" name="pannumber" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Aadhar Card Number</label>
                                <Field type="text" className="form-control" name="aadharCardNumber" />
                            </fieldset>
                            <div>
                                <button className="btn btn-success m-5" type="submit">Save</button>
                            </div>
                        </Form>
                    )}
                </Formik>
            </div>
        </div>
    );
}
