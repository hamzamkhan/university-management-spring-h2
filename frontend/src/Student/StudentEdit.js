import React, {useEffect, useState} from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { format } from 'date-fns'



const StudentEdit = () => {
    const initialFormState = {
        "name": '',
        "dob": '',
        "email": '',
        "fathers_name": '',
        "batch_year": '',
        "major": '',
        "degree": ''
    };

    const [student, setStudent] = useState(initialFormState);
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        console.log("ID === " + id);
        if(id !== 'new'){
            fetch(`${id}`)
            .then(response => response.json())
            .then(data => {console.log(data['response']);setStudent(data['response']);})
        }
    }, [id, setStudent]);

    const handleChange = (event) => {
        const{name, value} = event.target;

        setStudent({...student, [name]:value});
    }

    const handleSubmit = async(event) => {
        event.preventDefault();
        console.log(typeof student.dob);
        
        student.dob = format(new Date(student.dob), 'dd/mm/yyyy');
        console.log(student.dob);
        await fetch('/student' + (student.id ? '/' + student.id : ''), {
            method: (student.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(student)
        });
        setStudent(initialFormState);
        navigate('/students');
    }

    const title = <h2>{student.id ? 'Edit Student' : 'Add Student'}</h2>

    return(
        <div>
            <AppNavbar />
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type='text' name='name' id='name' value={student.name || ''} onChange={handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="fathers_name">Father's Name</Label>
                        <Input type='text' name='fathers_name' id='name' value={student.fathers_name || ''} onChange={handleChange} autoComplete="fathers_name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type='email' name='email' id='email' value={student.email || ''} onChange={handleChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="dob">Date of Birth</Label>
                        <Input type='date' name='dob' id='dob' value={student.dob || ''} onChange={handleChange} autoComplete="dob"/>
                        
                    </FormGroup>
                    <FormGroup>
                        <Label for="batch_year">Batch Year</Label>
                        <Input type='number' name='batch_year' id='batch_year' value={student.batch_year || ''} onChange={handleChange} autoComplete="batch_year"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="major">Major</Label>
                        <Input type='text' name='major' id='major' value={student.major || ''} onChange={handleChange} autoComplete="major"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="degree">Degree</Label>
                        <Input type='text' name='degree' id='degree' value={student.degree || ''} onChange={handleChange} autoComplete="degree"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/students">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    )
};

export default StudentEdit;