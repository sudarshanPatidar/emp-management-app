// import {Link} from 'react-router-dom'


// function WelcomeComponent() {


//     return (
//         <div className="WelcomeComponent">
//             <h1>Welcome</h1>
//             <div>
//                 Manage your designations - <Link to="/designations">Go here</Link>
//             </div>
//             <div>
//                 Manage your employees - <Link to="/employees">Go here</Link>
//             </div>
//         </div>
//     )
// }

// export default WelcomeComponent



import { Link } from 'react-router-dom';

function WelcomeComponent() {

    const welcomeStyle = {
        textAlign: 'center',
        padding: '30px',
        fontFamily: 'Arial, sans-serif',
        backgroundColor: '#f0f8ff', // Light blue background color
        minHeight: '44vh', // Full viewport height
    };

    const linkContainerStyle = {
        marginTop: '20px',
    };

    const linkStyle = {
        textDecoration: 'none',
        color: 'white',
        backgroundColor: '#007BFF',
        padding: '10px 20px',
        borderRadius: '5px',
        margin: '10px',
        display: 'inline-block',
    };

    return (
        <div className="WelcomeComponent" style={welcomeStyle}>
            <h1>Welcome</h1>
            <div style={linkContainerStyle}>
                Manage your designations - <Link to="/designations" style={linkStyle}>Go here</Link>
            </div>
            <div style={linkContainerStyle}>
                Manage your employees - <Link to="/employees" style={linkStyle}>Go here</Link>
            </div>
        </div>
    )
}

export default WelcomeComponent;
