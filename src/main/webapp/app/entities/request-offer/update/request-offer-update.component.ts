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
  financePartnersSharedCollection: IFinancePartner[] = [];

  editForm: RequestOfferFormGroup = this.requestOfferFormService.createRequestOfferFormGroup();

  constructor(
    protected requestOfferService: RequestOfferService,
    protected requestOfferFormService: RequestOfferFormService,
    protected financeRequestService: FinanceRequestService,
    protected financePartnerService: FinancePartnerService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareFinanceRequest = (o1: IFinanceRequest | null, o2: IFinanceRequest | null): boolean =>
    this.financeRequestService.compareFinanceRequest(o1, o2);

  compareFinancePartner = (o1: IFinancePartner | null, o2: IFinancePartner | null): boolean =>
    this.financePartnerService.compareFinancePartner(o1, o2);

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
    this.financePartnersSharedCollection = this.financePartnerService.addFinancePartnerToCollectionIfMissing<IFinancePartner>(
      this.financePartnersSharedCollection,
      requestOffer.financepartner,
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

    this.financePartnerService
      .query()
      .pipe(map((res: HttpResponse<IFinancePartner[]>) => res.body ?? []))
      .pipe(
        map((financePartners: IFinancePartner[]) =>
          this.financePartnerService.addFinancePartnerToCollectionIfMissing<IFinancePartner>(
            financePartners,
            this.requestOffer?.financepartner,
          ),
        ),
      )
      .subscribe((financePartners: IFinancePartner[]) => (this.financePartnersSharedCollection = financePartners));
  }
}
