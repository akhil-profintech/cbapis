import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';
import { FinancePartnerService } from 'app/entities/finance-partner/service/finance-partner.service';
import { DisbursementService } from '../service/disbursement.service';
import { IDisbursement } from '../disbursement.model';
import { DisbursementFormService, DisbursementFormGroup } from './disbursement-form.service';

@Component({
  standalone: true,
  selector: 'jhi-disbursement-update',
  templateUrl: './disbursement-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DisbursementUpdateComponent implements OnInit {
  isSaving = false;
  disbursement: IDisbursement | null = null;

  financeRequestsSharedCollection: IFinanceRequest[] = [];
  financePartnersSharedCollection: IFinancePartner[] = [];

  editForm: DisbursementFormGroup = this.disbursementFormService.createDisbursementFormGroup();

  constructor(
    protected disbursementService: DisbursementService,
    protected disbursementFormService: DisbursementFormService,
    protected financeRequestService: FinanceRequestService,
    protected financePartnerService: FinancePartnerService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareFinanceRequest = (o1: IFinanceRequest | null, o2: IFinanceRequest | null): boolean =>
    this.financeRequestService.compareFinanceRequest(o1, o2);

  compareFinancePartner = (o1: IFinancePartner | null, o2: IFinancePartner | null): boolean =>
    this.financePartnerService.compareFinancePartner(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ disbursement }) => {
      this.disbursement = disbursement;
      if (disbursement) {
        this.updateForm(disbursement);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const disbursement = this.disbursementFormService.getDisbursement(this.editForm);
    if (disbursement.id !== null) {
      this.subscribeToSaveResponse(this.disbursementService.update(disbursement));
    } else {
      this.subscribeToSaveResponse(this.disbursementService.create(disbursement));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDisbursement>>): void {
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

  protected updateForm(disbursement: IDisbursement): void {
    this.disbursement = disbursement;
    this.disbursementFormService.resetForm(this.editForm, disbursement);

    this.financeRequestsSharedCollection = this.financeRequestService.addFinanceRequestToCollectionIfMissing<IFinanceRequest>(
      this.financeRequestsSharedCollection,
      disbursement.financerequest,
    );
    this.financePartnersSharedCollection = this.financePartnerService.addFinancePartnerToCollectionIfMissing<IFinancePartner>(
      this.financePartnersSharedCollection,
      disbursement.financepartner,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.financeRequestService
      .query()
      .pipe(map((res: HttpResponse<IFinanceRequest[]>) => res.body ?? []))
      .pipe(
        map((financeRequests: IFinanceRequest[]) =>
          this.financeRequestService.addFinanceRequestToCollectionIfMissing<IFinanceRequest>(
            financeRequests,
            this.disbursement?.financerequest,
          ),
        ),
      )
      .subscribe((financeRequests: IFinanceRequest[]) => (this.financeRequestsSharedCollection = financeRequests));

    this.financePartnerService
      .query()
      .pipe(map((res: HttpResponse<IFinancePartner[]>) => res.body ?? []))
      .pipe(
        map((financePartners: IFinancePartner[]) =>
          this.financePartnerService.addFinancePartnerToCollectionIfMissing<IFinancePartner>(
            financePartners,
            this.disbursement?.financepartner,
          ),
        ),
      )
      .subscribe((financePartners: IFinancePartner[]) => (this.financePartnersSharedCollection = financePartners));
  }
}
