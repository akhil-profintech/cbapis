import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IClientCodes } from '../client-codes.model';
import { ClientCodesService } from '../service/client-codes.service';
import { ClientCodesFormService, ClientCodesFormGroup } from './client-codes-form.service';

@Component({
  standalone: true,
  selector: 'jhi-client-codes-update',
  templateUrl: './client-codes-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ClientCodesUpdateComponent implements OnInit {
  isSaving = false;
  clientCodes: IClientCodes | null = null;

  editForm: ClientCodesFormGroup = this.clientCodesFormService.createClientCodesFormGroup();

  constructor(
    protected clientCodesService: ClientCodesService,
    protected clientCodesFormService: ClientCodesFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ clientCodes }) => {
      this.clientCodes = clientCodes;
      if (clientCodes) {
        this.updateForm(clientCodes);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const clientCodes = this.clientCodesFormService.getClientCodes(this.editForm);
    if (clientCodes.id !== null) {
      this.subscribeToSaveResponse(this.clientCodesService.update(clientCodes));
    } else {
      this.subscribeToSaveResponse(this.clientCodesService.create(clientCodes));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClientCodes>>): void {
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

  protected updateForm(clientCodes: IClientCodes): void {
    this.clientCodes = clientCodes;
    this.clientCodesFormService.resetForm(this.editForm, clientCodes);
  }
}
