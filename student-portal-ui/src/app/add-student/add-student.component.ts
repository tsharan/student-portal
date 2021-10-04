import { Component, OnInit } from '@angular/core';
import { Student } from '../models/student';
import { StudentService } from '../service/student.service';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent implements OnInit {

  student: Student = new Student(null,'', '', '');
  user: string;

  constructor(
    private studentService: StudentService
  ) { }

  ngOnInit() {

  }

  createStudent(): void {
    this.studentService.createStudent(this.student)
      .subscribe( data => {
        alert("Student created successfully.");
      });
  };

}
