import React, {useEffect, useState} from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { format } from 'date-fns'
import PhoneInput from 'react-phone-input-2'


const ProfessorEdit = () => {
    const initialFormState = {
        "name": '',
        "mobile": '',
        "email": '',
        "dob": ''
    };
    

    const [professor, setProfessor] = useState(initialFormState);

    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {        
        if(id !== 'new'){
            fetch(`http://localhost:2000/api/professor/${id}`)
            .then(response => response.json())
            .then(data => {
                setProfessor(data['response']);
            });
        }
    }, [id, setProfessor]);

    const handleChange = (event) => {
        const{id, value} = event.target;

        setProfessor({...professor, [id]:value});
    }

    const handleSubmit = async(event) => {
        event.preventDefault();

        professor.dob = format(new Date(professor.dob), 'dd/MM/yyyy');
        
        await fetch('http://localhost:2000/api/professor' + (professor.id ? '/' + professor.id : ''), {
            method: (professor.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(professor)
        });
        setProfessor(initialFormState);
        navigate('/professors');
    }

    const title = <h2>{professor.id ? 'Edit Professor' : 'Add Professor'}</h2>
    const nameDisabled = professor.id ? true : false;

    return(
        <div>
            <AppNavbar />
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type='text' name='name' id='name' value={professor.name || ''} onChange={handleChange} autoComplete="name" disabled={nameDisabled}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type='email' name='email' id='email' value={professor.email || ''} onChange={handleChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="dob">Date of Birth</Label>
                        <Input type='date' name='dob' id='dob' value={professor.dob || ''} onChange={handleChange} autoComplete="dob"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="mobile">Mobile</Label>
                        <Input type='mobile' name='mobile' id='mobile' value={professor.mobile || ''} onChange={handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Button id="save-professor-btn" color="primary" type="submit">Save</Button>{' '}
                        <Button id="cancel-professor-btn" color="secondary" tag={Link} to="/professors">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    )
};

export default ProfessorEdit;