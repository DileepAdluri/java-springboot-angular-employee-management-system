import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  SimpleChanges,
  inject
} from '@angular/core';

import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { EmployeeModel } from '../models/employee';
import { EmployeeService } from '../services/employee';

@Component({
  selector: 'app-employee-form',
  imports: [ReactiveFormsModule],
  templateUrl: './employee-form.html',
  styleUrl: './employee-form.css'
})
export class EmployeeForm implements OnChanges {

  private readonly formBuilder = inject(FormBuilder);
  private readonly employeeService = inject(EmployeeService);

  @Input() employee: EmployeeModel | null = null;

  @Output() close = new EventEmitter<void>();
  @Output() employeeSaved = new EventEmitter<string>();

  employeeForm = this.formBuilder.group({
    id: ['', [
      Validators.required,
      Validators.pattern(/^\d+$/)
    ]],

    name: ['', [
      Validators.required,
      Validators.pattern(/^[A-Za-z\s]+$/)
    ]],

    email: ['', [
      Validators.required,
      Validators.email
    ]],

    phoneNumber: ['', [
      Validators.required,
      Validators.pattern(/^[6-9]\d{9}$/)
    ]],

    salary: [null as number | null, [
      Validators.required,
      Validators.min(0.01)
    ]]
  });

  submitted = false;
  isSaving = false;

  ngOnChanges(changes: SimpleChanges): void {

    if (!changes['employee']) {
      return;
    }

    if (this.employee) {

      this.employeeForm.patchValue({
        id: this.employee.id,
        name: this.employee.name,
        email: this.employee.email,
        phoneNumber: this.employee.phoneNumber,
        salary: this.employee.salary
      });

    } else {

      this.employeeForm.reset();

    }

    this.submitted = false;
  }

  saveEmployee(): void {

    this.submitted = true;

    if (this.employeeForm.invalid) {
      this.employeeForm.markAllAsTouched();
      return;
    }

    this.isSaving = true;

    const employee: EmployeeModel = {
      id: this.employeeForm.value.id ?? '',
      name: this.employeeForm.value.name ?? '',
      email: this.employeeForm.value.email ?? '',
      phoneNumber: this.employeeForm.value.phoneNumber ?? '',
      salary: this.employeeForm.value.salary ?? 0
    };

    if (this.employee) {

      this.employeeService.updateEmployee(
        this.employee.id,
        employee
      ).subscribe({
        next: () => {

          this.isSaving = false;

          this.employeeSaved.emit(
            `Employee ${employee.name} updated successfully.`
          );

          this.close.emit();

        },

        error: (error) => {

          console.error('Error updating employee:', error);

          this.isSaving = false;

        }
      });

      return;
    }

    this.employeeService.saveEmployee(employee).subscribe({
      next: () => {

        this.isSaving = false;

        this.employeeSaved.emit(
          `Employee ${employee.name} saved successfully.`
        );

        this.close.emit();

      },

      error: (error) => {

        console.error('Error saving employee:', error);

        this.isSaving = false;

      }
    });
  }

  closeForm(): void {
    this.close.emit();
  }

  get id() {
    return this.employeeForm.controls.id;
  }

  get name() {
    return this.employeeForm.controls.name;
  }

  get email() {
    return this.employeeForm.controls.email;
  }

  get phoneNumber() {
    return this.employeeForm.controls.phoneNumber;
  }

  get salary() {
    return this.employeeForm.controls.salary;
  }
}