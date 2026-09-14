import { ChangeDetectorRef, Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { EmployeeModel } from './models/employee';
import { EmployeeService } from './services/employee';
import { EmployeeForm } from './employee-form/employee-form';

@Component({
  selector: 'app-employee',
  imports: [FormsModule, EmployeeForm],
  templateUrl: './employee.html',
  styleUrl: './employee.css'
})
export class Employee implements OnInit {

  private readonly employeeService = inject(EmployeeService);
  private readonly changeDetectorRef = inject(ChangeDetectorRef);

  employees: EmployeeModel[] = [];

  searchText = '';

  currentPage = 1;
  pageSize = 10;

  showForm = false;

  selectedEmployee: EmployeeModel | null = null;

  successMessage = '';

  showDeleteConfirmation = false;
  employeeToDelete: EmployeeModel | null = null;
  isDeleting = false;

  ngOnInit(): void {
    this.loadEmployees();
  }

  loadEmployees(): void {
    this.employeeService.getEmployees().subscribe({
      next: (response) => {
        this.employees = response.data ?? [];

        if (this.currentPage > this.totalPages && this.totalPages > 0) {
          this.currentPage = this.totalPages;
        }

        this.changeDetectorRef.detectChanges();
      },
      error: (error) => {
        console.error('Error fetching employees:', error);

        this.employees = [];

        this.changeDetectorRef.detectChanges();
      }
    });
  }

  openAddEmployee(): void {
    this.selectedEmployee = null;
    this.showForm = true;
  }

  openEditEmployee(employee: EmployeeModel): void {
    this.selectedEmployee = { ...employee };
    this.showForm = true;
  }

  closeForm(): void {
    this.showForm = false;
    this.selectedEmployee = null;
    this.loadEmployees();
  }

  showSuccessMessage(message: string): void {
    this.successMessage = message;

    setTimeout(() => {
      this.successMessage = '';
      this.changeDetectorRef.detectChanges();
    }, 3000);

    this.changeDetectorRef.detectChanges();
  }

  openDeleteConfirmation(employee: EmployeeModel): void {
    this.employeeToDelete = employee;
    this.showDeleteConfirmation = true;
  }

  closeDeleteConfirmation(): void {
    if (this.isDeleting) {
      return;
    }

    this.showDeleteConfirmation = false;
    this.employeeToDelete = null;
  }

  confirmDelete(): void {
    if (!this.employeeToDelete) {
      return;
    }

    this.isDeleting = true;

    const employeeId = this.employeeToDelete.id;
    const employeeName = this.employeeToDelete.name;

    this.employeeService.deleteEmployee(employeeId).subscribe({
      next: () => {

        this.isDeleting = false;
        this.showDeleteConfirmation = false;
        this.employeeToDelete = null;

        this.loadEmployees();

        this.showSuccessMessage(
          `Employee ${employeeName} deleted successfully.`
        );
      },

      error: (error) => {

        console.error('Error deleting employee:', error);

        this.isDeleting = false;

        this.changeDetectorRef.detectChanges();
      }
    });
  }

  get filteredEmployees(): EmployeeModel[] {
    const searchValue = this.searchText.trim().toLowerCase();

    if (!searchValue) {
      return this.employees;
    }

    return this.employees.filter((employee) =>
      String(employee.id).toLowerCase().includes(searchValue) ||
      employee.name.toLowerCase().includes(searchValue) ||
      employee.email.toLowerCase().includes(searchValue) ||
      employee.phoneNumber.toLowerCase().includes(searchValue)
    );
  }

  get displayedEmployees(): EmployeeModel[] {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;

    return this.filteredEmployees.slice(startIndex, endIndex);
  }

  searchEmployees(): void {
    this.currentPage = 1;
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
    }
  }

  previousPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
    }
  }

  get totalPages(): number {
    return Math.ceil(this.filteredEmployees.length / this.pageSize);
  }

  get startRecord(): number {
    if (this.filteredEmployees.length === 0) {
      return 0;
    }

    return (this.currentPage - 1) * this.pageSize + 1;
  }

  get endRecord(): number {
    return Math.min(
      this.currentPage * this.pageSize,
      this.filteredEmployees.length
    );
  }
}