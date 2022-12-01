import React from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

const Home = () => {
    return (
        <div>
            <AppNavbar />
            <Container fluid>
                <Button id='manage-students-btn' color='link'><Link to="/students">Manage Students</Link></Button>
                <Button id='manage-professors-btn' color='link'><Link to="/professors">Manage Professors</Link></Button>
            </Container>
        </div>
    );
}

export default Home;
