import { Component, OnInit } from '@angular/core';
import { StudentService } from '../service/student.service';
import { Student } from '../models/student';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  students: Student[];

  constructor(
    private studentService:StudentService
  ) {}

  ngOnInit() {
    this.studentService.getStudents().subscribe(
      response =>this.handleSuccessfulResponse(response),
    );
  }

  deleteStudent(student: Student): void {
    this.studentService.deleteStudent(student)
      .subscribe( data => {
        this.students = this.students.filter(u => u !== student);
      })
  };

  handleSuccessfulResponse(response) {
    this.students=response;
  }

}
