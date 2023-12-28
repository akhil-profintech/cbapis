import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { ISettlement } from '../settlement.model';
import { SettlementService } from '../service/settlement.service';
import { SettlementFormService, SettlementFormGroup } from './settlement-form.service';

@Component({
  standalone: true,
  selector: 'jhi-settlement-update',
  templateUrl: './settlement-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class SettlementUpdateComponent implements OnInit {
  isSaving = false;
  settlement: ISettlement | null = null;

  financeRequestsSharedCollection: IFinanceRequest[] = [];

  editForm: SettlementFormGroup = this.settlementFormService.createSettlementFormGroup();

  constructor(
    protected settlementService: SettlementService,
    protected settlementFormService: SettlementFormService,
    protected financeRequestService: FinanceRequestService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareFinanceRequest = (o1: IFinanceRequest | null, o2: IFinanceRequest | null): boolean =>
    this.financeRequestService.compareFinanceRequest(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ settlement }) => {
      this.settlement = settlement;
      if (settlement) {
        this.updateForm(settlement);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const settlement = this.settlementFormService.getSettlement(this.editForm);
    if (settlement.id !== null) {
      this.subscribeToSaveResponse(this.settlementService.update(settlement));
    } else {
      this.subscribeToSaveResponse(this.settlementService.create(settlement));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISettlement>>): void {
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

  protected updateForm(settlement: ISettlement): void {
    this.settlement = settlement;
    this.settlementFormService.resetForm(this.editForm, settlement);

    this.financeRequestsSharedCollection = this.financeRequestService.addFinanceRequestToCollectionIfMissing<IFinanceRequest>(
      this.financeRequestsSharedCollection,
      settlement.financerequest,
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
            this.settlement?.financerequest,
          ),
        ),
      )
      .subscribe((financeRequests: IFinanceRequest[]) => (this.financeRequestsSharedCollection = financeRequests));
  }
}
