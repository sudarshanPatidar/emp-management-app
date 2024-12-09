import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import LogoutComponent from './LogoutComponent'
import HeaderComponent from './HeaderComponent'
import ListDesignationsComponent from './ListDesignationsComponent'
import ErrorComponent from './ErrorComponent'
import WelcomeComponent from './WelcomeComponent'
import LoginComponent from './LoginComponent'
import DesignationComponent from './DesignationComponent'
import AuthProvider, { useAuth } from './security/AuthContext'
import ListEmployeesComponent from './ListEmployeesComponent'

import './DesignationsApp.css'
import EmployeeComponent from './EmployeeComponent'
import FooterComponent from './FooterComponent'

function AuthenticatedRoute({children}) {
    const authContext = useAuth()
    
    if(authContext.isAuthenticated)
        return children

    return <Navigate to="/" />
}

export default function DesignationsApp() {
    return (
        <div className="DesignationsApp">
            <AuthProvider>
                <BrowserRouter>
                    <HeaderComponent />
                    <Routes>
                        <Route path='/' element={ <LoginComponent /> } />
                        <Route path='/login' element={ <LoginComponent /> } />
                        
                        <Route path='/welcome' element={
                            <AuthenticatedRoute>
                                <WelcomeComponent />
                            </AuthenticatedRoute> 
                        } />
                        
                        <Route path='/designations' element={
                            <AuthenticatedRoute>
                                <ListDesignationsComponent/> 
                            </AuthenticatedRoute>
                        } />

                        <Route path='/employees' element={
                            <AuthenticatedRoute>
                                <ListEmployeesComponent/> 
                            </AuthenticatedRoute>
                        } />

                        <Route path='/designations/:code' element={
                            <AuthenticatedRoute>
                                <DesignationComponent /> 
                            </AuthenticatedRoute>
                        } />

                        <Route path='/employees/:employeeId' element={
                            <AuthenticatedRoute>
                                <EmployeeComponent/> 
                            </AuthenticatedRoute>
                        } />
  

                        <Route path='/logout' element={
                            <AuthenticatedRoute>
                                <LogoutComponent /> 
                            </AuthenticatedRoute>
                        } />
                        
                        <Route path='*' element={<ErrorComponent /> } />

                    </Routes>
                    <FooterComponent/>
                </BrowserRouter>
            </AuthProvider>
        </div>
    )
}