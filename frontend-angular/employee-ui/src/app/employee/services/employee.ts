import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { EmployeeModel } from '../models/employee';
import { ApiResponse } from '../models/api-response';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private readonly http = inject(HttpClient);

  private readonly apiUrl = 'http://localhost:8080/employee';

  getEmployees(): Observable<ApiResponse<EmployeeModel[]>> {
    return this.http.get<ApiResponse<EmployeeModel[]>>(
      `${this.apiUrl}/getEmployee`
    );
  }

  saveEmployee(employee: EmployeeModel): Observable<ApiResponse<EmployeeModel>> {
    return this.http.post<ApiResponse<EmployeeModel>>(
      `${this.apiUrl}/saveEmployee`,
      employee
    );
  }

  updateEmployee(
    id: string,
    employee: EmployeeModel
  ): Observable<ApiResponse<EmployeeModel>> {
    return this.http.put<ApiResponse<EmployeeModel>>(
      `${this.apiUrl}/updateEmployee/${id}`,
      employee
    );
  }

  deleteEmployee(id: string): Observable<ApiResponse<string>> {
    return this.http.delete<ApiResponse<string>>(
      `${this.apiUrl}/deleteEmployee/${id}`
    );
  }
}