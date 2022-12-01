import React, { useEffect, useState} from 'react';
import { Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {Link} from 'react-router-dom';

const ProfessorList = () => {
    document.title='Professor Management'
    const[professors, setProfessors] = useState([]);
    const[loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);

        fetch("http://localhost:2000/api/professor/list")
        .then(response => response.json())
        .then(data => {
            setProfessors(data['response']);
            setLoading(false);
        })
    }, []);

    const remove = async(id) => {
        await fetch(`http://localhost:2000/api/professor/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type':'application/json'
            }
        }).then(() => {
            let updatedProfessors = [...professors].filter(i => i.id !== id);
            setProfessors(updatedProfessors);
        });
    }

    if(loading){
        return <p>Loading...</p>;
    }

    const professorList = professors.map(professor => {
        return <tr key={professor.id}>
            <td style={{whiteSpace: 'nowrap'}}>{professor.name}</td>
            <td>{professor.email}</td>
            <td>
                <ButtonGroup>
                    <Button size='sm' color='primary' tag={Link} to={"/professor/" + professor.id}>Edit</Button>
                    <Button size='sm' color='danger' onClick={() => remove(professor.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <div className='float-end'>
                    <Button id='add-professor-btn' color='success' tag={Link} to="/professor/new">Add Professor</Button>
                </div>
                <h3>Professor Management</h3>
                <Table className='mt-2'>
                    <thead>
                        <tr>
                            <th width="50%">Name</th>
                            <th width="50%">Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        {professorList}
                    </tbody>
                </Table>
            </Container>
        </div>
    );
};

export default ProfessorList;