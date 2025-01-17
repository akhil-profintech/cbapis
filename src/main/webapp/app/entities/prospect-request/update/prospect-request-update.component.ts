import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IProspectRequest } from '../prospect-request.model';
import { ProspectRequestService } from '../service/prospect-request.service';
import { ProspectRequestFormService, ProspectRequestFormGroup } from './prospect-request-form.service';

@Component({
  standalone: true,
  selector: 'jhi-prospect-request-update',
  templateUrl: './prospect-request-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ProspectRequestUpdateComponent implements OnInit {
  isSaving = false;
  prospectRequest: IProspectRequest | null = null;

  editForm: ProspectRequestFormGroup = this.prospectRequestFormService.createProspectRequestFormGroup();

  constructor(
    protected prospectRequestService: ProspectRequestService,
    protected prospectRequestFormService: ProspectRequestFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ prospectRequest }) => {
      this.prospectRequest = prospectRequest;
      if (prospectRequest) {
        this.updateForm(prospectRequest);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const prospectRequest = this.prospectRequestFormService.getProspectRequest(this.editForm);
    if (prospectRequest.id !== null) {
      this.subscribeToSaveResponse(this.prospectRequestService.update(prospectRequest));
    } else {
      this.subscribeToSaveResponse(this.prospectRequestService.create(prospectRequest));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProspectRequest>>): void {
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

  protected updateForm(prospectRequest: IProspectRequest): void {
    this.prospectRequest = prospectRequest;
    this.prospectRequestFormService.resetForm(this.editForm, prospectRequest);
  }
}
