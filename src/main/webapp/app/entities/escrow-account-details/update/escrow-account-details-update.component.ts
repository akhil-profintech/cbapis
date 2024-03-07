import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IEscrowAccountDetails } from '../escrow-account-details.model';
import { EscrowAccountDetailsService } from '../service/escrow-account-details.service';
import { EscrowAccountDetailsFormService, EscrowAccountDetailsFormGroup } from './escrow-account-details-form.service';

@Component({
  standalone: true,
  selector: 'jhi-escrow-account-details-update',
  templateUrl: './escrow-account-details-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class EscrowAccountDetailsUpdateComponent implements OnInit {
  isSaving = false;
  escrowAccountDetails: IEscrowAccountDetails | null = null;

  editForm: EscrowAccountDetailsFormGroup = this.escrowAccountDetailsFormService.createEscrowAccountDetailsFormGroup();

  constructor(
    protected escrowAccountDetailsService: EscrowAccountDetailsService,
    protected escrowAccountDetailsFormService: EscrowAccountDetailsFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ escrowAccountDetails }) => {
      this.escrowAccountDetails = escrowAccountDetails;
      if (escrowAccountDetails) {
        this.updateForm(escrowAccountDetails);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const escrowAccountDetails = this.escrowAccountDetailsFormService.getEscrowAccountDetails(this.editForm);
    if (escrowAccountDetails.id !== null) {
      this.subscribeToSaveResponse(this.escrowAccountDetailsService.update(escrowAccountDetails));
    } else {
      this.subscribeToSaveResponse(this.escrowAccountDetailsService.create(escrowAccountDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEscrowAccountDetails>>): void {
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

  protected updateForm(escrowAccountDetails: IEscrowAccountDetails): void {
    this.escrowAccountDetails = escrowAccountDetails;
    this.escrowAccountDetailsFormService.resetForm(this.editForm, escrowAccountDetails);
  }
}
