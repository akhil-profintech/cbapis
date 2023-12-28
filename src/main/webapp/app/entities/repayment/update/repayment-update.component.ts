import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { IRepayment } from '../repayment.model';
import { RepaymentService } from '../service/repayment.service';
import { RepaymentFormService, RepaymentFormGroup } from './repayment-form.service';

@Component({
  standalone: true,
  selector: 'jhi-repayment-update',
  templateUrl: './repayment-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class RepaymentUpdateComponent implements OnInit {
  isSaving = false;
  repayment: IRepayment | null = null;

  financeRequestsSharedCollection: IFinanceRequest[] = [];

  editForm: RepaymentFormGroup = this.repaymentFormService.createRepaymentFormGroup();

  constructor(
    protected repaymentService: RepaymentService,
    protected repaymentFormService: RepaymentFormService,
    protected financeRequestService: FinanceRequestService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareFinanceRequest = (o1: IFinanceRequest | null, o2: IFinanceRequest | null): boolean =>
    this.financeRequestService.compareFinanceRequest(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ repayment }) => {
      this.repayment = repayment;
      if (repayment) {
        this.updateForm(repayment);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const repayment = this.repaymentFormService.getRepayment(this.editForm);
    if (repayment.id !== null) {
      this.subscribeToSaveResponse(this.repaymentService.update(repayment));
    } else {
      this.subscribeToSaveResponse(this.repaymentService.create(repayment));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRepayment>>): void {
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

  protected updateForm(repayment: IRepayment): void {
    this.repayment = repayment;
    this.repaymentFormService.resetForm(this.editForm, repayment);

    this.financeRequestsSharedCollection = this.financeRequestService.addFinanceRequestToCollectionIfMissing<IFinanceRequest>(
      this.financeRequestsSharedCollection,
      repayment.financerequest,
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
            this.repayment?.financerequest,
          ),
        ),
      )
      .subscribe((financeRequests: IFinanceRequest[]) => (this.financeRequestsSharedCollection = financeRequests));
  }
}
