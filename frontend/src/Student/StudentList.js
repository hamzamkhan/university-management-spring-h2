import React, { useEffect, useState} from 'react';
import { Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {Link} from 'react-router-dom';

const StudentList = () => {
    document.title='Student Management'
    const[students, setStudents] = useState([]);
    const[loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);

        fetch("student/list")
        .then(response => response.json())
        .then(data => {
            setStudents(data['response']);
            setLoading(false);
        })
    }, []);

    const remove = async(id) => {
        await fetch(`student/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type':'application/json'
            }
        }).then(() => {
            let updatedStudents = [...students].filter(i => i.id !== id);
            setStudents(updatedStudents);
        });
    }

    if(loading){
        return <p>Loading...</p>;
    }

    const studentList = students.map(student => {
        return <tr key={student.id}>
            <td style={{whiteSpace: 'nowrap'}}>{student.name}</td>
            <td>{student.email}</td>
            <td>
                <ButtonGroup>
                    <Button size='sm' color='primary' tag={Link} to={"/student/" + student.id}>Edit</Button>
                    <Button size='sm' color='danger' onClick={() => remove(student.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <div className='float-end'>
                    <Button color='success' tag={Link} to="/student/new">Add Student</Button>
                </div>
                <h3>Student Management</h3>
                <Table className='mt-2'>
                    <thead>
                        <tr>
                            <th width="50%">Name</th>
                            <th width="50%">Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        {studentList}
                    </tbody>
                </Table>
            </Container>
        </div>
    );
};

export default StudentList;