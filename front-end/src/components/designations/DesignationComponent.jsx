// import { useEffect, useState } from 'react'
// import {useParams, useNavigate} from 'react-router-dom'
// import { retrieveDesignationByCodeApi, updateDesignationApi, createDesignationApi } from './api/DesignationApiService'
// import {Formik, Form, Field} from 'formik'

// export default function DesignationComponet() {
    
//     const {code} = useParams()
    
//     const[title, setTitle] = useState('')

//     const [error, setError] = useState('');

//     const navigate = useNavigate()
    
//     // const username = authContext.username
    
//     useEffect(
//         () => retrieveDesignations(), [code]
//         )

//     function retrieveDesignations(){
//         if(code != -1) {
//             retrieveDesignationByCodeApi(code)
//             .then(response => {
//                 setTitle(response.data.title)
//                 console.log(title)
//             })
//             .catch(error => console.log(error))
//         }
//     }

//     function onSubmit(values) {
//         const designation = {
//             code: code,
//             title: values.title,
//         }


//         if(code==-1) {
//             const designation = {
//                 code: 0,
//                 title: values.title,
//             }
//             createDesignationApi(designation)
//             .then(response => {
//                 navigate('/designations');
//             })
//             .catch(error => {
//                 // console.log(error);
//                 if (error.response && error.response.status === 409) {
//                     setError(`Conflict Error: ${error.response.data}`);
//                 } else {
//                     setError('Error creating designation');
//                 }
//             });
    
//         } else {
//             const designation = {
//                 code: code,
//                 title: values.title,
//             }

//             // console.log(designation)


//             updateDesignationApi(designation)
//             .then(response => {
//                 if (response.status !== 201) {
//                     if (response.status === 409) {
//                         setError(`Conflict Error: ${response.data}`);
//                     } else {
//                         setError(`Error: ${response.data}`);
//                     }
//                 } else {
//                     navigate('/designations');
//                 }
//             })
//             .catch(error => {
//                 console.log(error);
//                 if (error.response && error.response.status === 409) {
//                     setError(`Conflict Error: ${error.response.data}`);
//                 } else {
//                     setError('Error updating designation');
//                 }
//             });

            
//         }
//     }

//     return (
//         <div className="container">
//             <h1>Enter Designation Details </h1>
//             <div>
//             {error && <div className="alert alert-warning m-3">{error}</div>}
//                 <Formik initialValues={ { title } } 
//                     enableReinitialize = {true}
//                     onSubmit = {onSubmit}
//                 >
//                 {
//                     (props) => (
//                         <Form>


//                             <fieldset className="form-group">
//                                 <label>Title</label>
//                                 <Field type="text" className="form-control" name="title" />
//                             </fieldset>
//                             <div>
//                                 <button className="btn btn-success m-5" type="submit">Save</button>
//                             </div>
//                         </Form>
//                     )
//                 }
//                 </Formik>
//             </div>

//         </div>
//     )
// }





import { useEffect, useState, useCallback } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { retrieveDesignationByCodeApi, updateDesignationApi, createDesignationApi } from './api/DesignationApiService';
import { Formik, Form, Field } from 'formik';

export default function DesignationComponent() {
    const { code } = useParams();
    const [title, setTitle] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const retrieveDesignations = useCallback(() => {
        if (code !=  -1) {
            retrieveDesignationByCodeApi(code)
                .then(response => {
                    setTitle(response.data.title);
                })
                .catch(error => console.error(error));
        }
    }, [code]);

    useEffect(() => {
        retrieveDesignations();
    }, [retrieveDesignations]);

    function onSubmit(values) {
        const designation = {
            code: code,
            title: values.title,
        };

        if (code == -1) {
            const newDesignation = {
                code: 0,
                title: values.title,
            };
            createDesignationApi(newDesignation)
                .then(response => {
                    navigate('/designations');
                })
                .catch(error => {
                    if (error.response && error.response.status === 409) {
                        setError(`Conflict Error: ${error.response.data}`);
                    } else {
                        setError('Error creating designation');
                    }
                });
        } else {
            updateDesignationApi(designation)
                .then(response => {
                    if (response.status !== 201) {
                        if (response.status === 409) {
                            setError(`Conflict Error: ${response.data}`);
                        } else {
                            setError(`Error: ${response.data}`);
                        }
                    } else {
                        navigate('/designations');
                    }
                })
                .catch(error => {
                    if (error.response && error.response.status === 409) {
                        setError(`Conflict Error: ${error.response.data}`);
                    } else {
                        setError('Error updating designation');
                    }
                });
        }
    }

    return (
        <div className="container">
            <h1>Enter Designation Details</h1>
            <div>
                {error && <div className="alert alert-warning m-3">{error}</div>}
                <Formik initialValues={{ title }} enableReinitialize={true} onSubmit={onSubmit}>
                    {(props) => (
                        <Form>
                            <fieldset className="form-group">
                                <label>Title</label>
                                <Field type="text" className="form-control" name="title" />
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
