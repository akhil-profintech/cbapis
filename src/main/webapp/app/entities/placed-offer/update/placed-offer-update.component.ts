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
import { PlacedOfferService } from '../service/placed-offer.service';
import { IPlacedOffer } from '../placed-offer.model';
import { PlacedOfferFormService, PlacedOfferFormGroup } from './placed-offer-form.service';

@Component({
  standalone: true,
  selector: 'jhi-placed-offer-update',
  templateUrl: './placed-offer-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class PlacedOfferUpdateComponent implements OnInit {
  isSaving = false;
  placedOffer: IPlacedOffer | null = null;

  financeRequestsSharedCollection: IFinanceRequest[] = [];
  financePartnersSharedCollection: IFinancePartner[] = [];

  editForm: PlacedOfferFormGroup = this.placedOfferFormService.createPlacedOfferFormGroup();

  constructor(
    protected placedOfferService: PlacedOfferService,
    protected placedOfferFormService: PlacedOfferFormService,
    protected financeRequestService: FinanceRequestService,
    protected financePartnerService: FinancePartnerService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareFinanceRequest = (o1: IFinanceRequest | null, o2: IFinanceRequest | null): boolean =>
    this.financeRequestService.compareFinanceRequest(o1, o2);

  compareFinancePartner = (o1: IFinancePartner | null, o2: IFinancePartner | null): boolean =>
    this.financePartnerService.compareFinancePartner(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ placedOffer }) => {
      this.placedOffer = placedOffer;
      if (placedOffer) {
        this.updateForm(placedOffer);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const placedOffer = this.placedOfferFormService.getPlacedOffer(this.editForm);
    if (placedOffer.id !== null) {
      this.subscribeToSaveResponse(this.placedOfferService.update(placedOffer));
    } else {
      this.subscribeToSaveResponse(this.placedOfferService.create(placedOffer));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlacedOffer>>): void {
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

  protected updateForm(placedOffer: IPlacedOffer): void {
    this.placedOffer = placedOffer;
    this.placedOfferFormService.resetForm(this.editForm, placedOffer);

    this.financeRequestsSharedCollection = this.financeRequestService.addFinanceRequestToCollectionIfMissing<IFinanceRequest>(
      this.financeRequestsSharedCollection,
      placedOffer.financerequest,
    );
    this.financePartnersSharedCollection = this.financePartnerService.addFinancePartnerToCollectionIfMissing<IFinancePartner>(
      this.financePartnersSharedCollection,
      placedOffer.financepartner,
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
            this.placedOffer?.financerequest,
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
            this.placedOffer?.financepartner,
          ),
        ),
      )
      .subscribe((financePartners: IFinancePartner[]) => (this.financePartnersSharedCollection = financePartners));
  }
}
