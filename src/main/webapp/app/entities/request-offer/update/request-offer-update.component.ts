import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';
import { CBCREProcessService } from 'app/entities/cbcre-process/service/cbcre-process.service';
import { RequestOfferService } from '../service/request-offer.service';
import { IRequestOffer } from '../request-offer.model';
import { RequestOfferFormService, RequestOfferFormGroup } from './request-offer-form.service';

@Component({
  standalone: true,
  selector: 'jhi-request-offer-update',
  templateUrl: './request-offer-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class RequestOfferUpdateComponent implements OnInit {
  isSaving = false;
  requestOffer: IRequestOffer | null = null;

  financeRequestsSharedCollection: IFinanceRequest[] = [];
  cBCREProcessesSharedCollection: ICBCREProcess[] = [];

  editForm: RequestOfferFormGroup = this.requestOfferFormService.createRequestOfferFormGroup();

  constructor(
    protected requestOfferService: RequestOfferService,
    protected requestOfferFormService: RequestOfferFormService,
    protected financeRequestService: FinanceRequestService,
    protected cBCREProcessService: CBCREProcessService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareFinanceRequest = (o1: IFinanceRequest | null, o2: IFinanceRequest | null): boolean =>
    this.financeRequestService.compareFinanceRequest(o1, o2);

  compareCBCREProcess = (o1: ICBCREProcess | null, o2: ICBCREProcess | null): boolean =>
    this.cBCREProcessService.compareCBCREProcess(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requestOffer }) => {
      this.requestOffer = requestOffer;
      if (requestOffer) {
        this.updateForm(requestOffer);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const requestOffer = this.requestOfferFormService.getRequestOffer(this.editForm);
    if (requestOffer.id !== null) {
      this.subscribeToSaveResponse(this.requestOfferService.update(requestOffer));
    } else {
      this.subscribeToSaveResponse(this.requestOfferService.create(requestOffer));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequestOffer>>): void {
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

  protected updateForm(requestOffer: IRequestOffer): void {
    this.requestOffer = requestOffer;
    this.requestOfferFormService.resetForm(this.editForm, requestOffer);

    this.financeRequestsSharedCollection = this.financeRequestService.addFinanceRequestToCollectionIfMissing<IFinanceRequest>(
      this.financeRequestsSharedCollection,
      requestOffer.financerequest,
    );
    this.cBCREProcessesSharedCollection = this.cBCREProcessService.addCBCREProcessToCollectionIfMissing<ICBCREProcess>(
      this.cBCREProcessesSharedCollection,
      requestOffer.cbcreprocess,
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
            this.requestOffer?.financerequest,
          ),
        ),
      )
      .subscribe((financeRequests: IFinanceRequest[]) => (this.financeRequestsSharedCollection = financeRequests));

    this.cBCREProcessService
      .query()
      .pipe(map((res: HttpResponse<ICBCREProcess[]>) => res.body ?? []))
      .pipe(
        map((cBCREProcesses: ICBCREProcess[]) =>
          this.cBCREProcessService.addCBCREProcessToCollectionIfMissing<ICBCREProcess>(cBCREProcesses, this.requestOffer?.cbcreprocess),
        ),
      )
      .subscribe((cBCREProcesses: ICBCREProcess[]) => (this.cBCREProcessesSharedCollection = cBCREProcesses));
  }
}
