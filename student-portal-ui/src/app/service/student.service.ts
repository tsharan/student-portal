import { Student } from '../models/student';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class StudentService {

  constructor(private httpClient:HttpClient) { }

  getStudents() {
    return this.httpClient.get<Student[]>('http://localhost:8081/students');
  }

  public deleteStudent(student: Student) {
    return this.httpClient.delete<Student>("http://localhost:8081/students" + "/"+ student.id);
  }

  public createStudent(student: Student) {
    return this.httpClient.post<Student>(`http://localhost:8081/students`, student);
  }
}
