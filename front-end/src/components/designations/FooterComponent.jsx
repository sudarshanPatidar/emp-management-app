import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function FooterComponent() {
    return (
        <footer className="bg-dark text-white mt-5 p-4 text-center">
            <div className="container">
                <p>&copy; 2024 Your Company Name. All rights reserved.</p>
                <p>
                    <a href="/privacy" className="text-white">Privacy Policy</a> | 
                    <a href="/terms" className="text-white ml-2">Terms of Service</a>
                </p>
            </div>
        </footer>
    );
}

export default FooterComponent;
