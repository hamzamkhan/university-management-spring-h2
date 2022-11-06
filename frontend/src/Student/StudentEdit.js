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
    const [majors, setMajors] = useState([]);
    const [degrees, setDegrees] = useState([]);

    const navigate = useNavigate();
    const { id } = useParams();

    const getMajors = async args => {
        const res = await fetch("http://localhost:2000/api/major/list");
        const response = await res.json();
        const resMajors = [];
        resMajors.push({'id': "-1", "name":"--Select Major--"});
        response['response'].map(value => resMajors.push(value))
        setMajors(resMajors);
    }

    const getDegrees = async args => {
        const res = await fetch("http://localhost:2000/api/degree/list");
        const response = await res.json();
        const resDegrees = [];
        resDegrees.push({'id': "-1", "name":"--Select Degree--"});
        response['response'].map(value => resDegrees.push(value))
        setDegrees(resDegrees);
    }

    useEffect(() => {
        
        getDegrees();
        getMajors();
        

        if(id !== 'new'){
            fetch(`http://localhost:2000/api/student/${id}`)
            .then(response => response.json())
            .then(data => {
                setStudent(data['response']);
            });
        }
    }, [id, setStudent]);

    const handleChange = (event) => {
        const{id, value} = event.target;

        setStudent({...student, [id]:value});
    }

    const handleSubmit = async(event) => {
        event.preventDefault();

        student.dob = format(new Date(student.dob), 'dd/MM/yyyy');
        for(let i = 0; i < degrees.length; i++){
            if(student.degree == degrees[i].name){
                student.degree = degrees[i].id;
                break;
            }
        }
        for(let i = 0; i < degrees.length; i++){
            if(student.major == majors[i].name){
                student.major = majors[i].id;
                break;
            }
        }
        
        await fetch('http://localhost:2000/api/student' + (student.id ? '/' + student.id : ''), {
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

    const degreeList = degrees.map(degree => {
        return <option value={degree.id}>{degree.name}</option>     
    });

    const majorList = majors.map(major => {
        return <option value={major.id}>{major.name}</option> 
        
    });



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
                        <Input type='text' name='fathers_name' id='fathers_name' value={student.fathers_name || ''} onChange={handleChange} autoComplete="fathers_name"/>
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
                        <Input type='text' name='major'  readOnly disabled defaultValue={student.major} autoComplete="major"/>
                        <select  id='major' value={student.major} onChange={handleChange} name='major'>
                            {majorList}
                        </select>
                    </FormGroup>
                    <FormGroup>
                        <Label for="degree">Degree</Label>
                        <Input type='text' name='degree'  readOnly disabled defaultValue={student.degree} autoComplete="degree"/>
                        <select value={student.degree}  id='degree' onChange={handleChange} name='degree'>
                            {degreeList}
                        </select>
                    </FormGroup>
                    <FormGroup>
                        <Button id="save-student-btn" color="primary" type="submit">Save</Button>{' '}
                        <Button id="cancel-student-btn" color="secondary" tag={Link} to="/students">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    )
};

export default StudentEdit;