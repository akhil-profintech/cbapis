import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { Persona } from 'app/entities/enumerations/persona.model';
import { IUserDtls } from '../user-dtls.model';
import { UserDtlsService } from '../service/user-dtls.service';
import { UserDtlsFormService, UserDtlsFormGroup } from './user-dtls-form.service';

@Component({
  standalone: true,
  selector: 'jhi-user-dtls-update',
  templateUrl: './user-dtls-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class UserDtlsUpdateComponent implements OnInit {
  isSaving = false;
  userDtls: IUserDtls | null = null;
  personaValues = Object.keys(Persona);

  editForm: UserDtlsFormGroup = this.userDtlsFormService.createUserDtlsFormGroup();

  constructor(
    protected userDtlsService: UserDtlsService,
    protected userDtlsFormService: UserDtlsFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userDtls }) => {
      this.userDtls = userDtls;
      if (userDtls) {
        this.updateForm(userDtls);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userDtls = this.userDtlsFormService.getUserDtls(this.editForm);
    if (userDtls.id !== null) {
      this.subscribeToSaveResponse(this.userDtlsService.update(userDtls));
    } else {
      this.subscribeToSaveResponse(this.userDtlsService.create(userDtls));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserDtls>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(userDtls: IUserDtls): void {
    this.userDtls = userDtls;
    this.userDtlsFormService.resetForm(this.editForm, userDtls);
  }
}
