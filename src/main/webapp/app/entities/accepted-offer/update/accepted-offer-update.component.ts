import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';
import { AnchorTraderService } from 'app/entities/anchor-trader/service/anchor-trader.service';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';
import { FinancePartnerService } from 'app/entities/finance-partner/service/finance-partner.service';
import { AcceptedOfferService } from '../service/accepted-offer.service';
import { IAcceptedOffer } from '../accepted-offer.model';
import { AcceptedOfferFormService, AcceptedOfferFormGroup } from './accepted-offer-form.service';

@Component({
  standalone: true,
  selector: 'jhi-accepted-offer-update',
  templateUrl: './accepted-offer-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AcceptedOfferUpdateComponent implements OnInit {
  isSaving = false;
  acceptedOffer: IAcceptedOffer | null = null;

  financeRequestsSharedCollection: IFinanceRequest[] = [];
  anchorTradersSharedCollection: IAnchorTrader[] = [];
  financePartnersSharedCollection: IFinancePartner[] = [];

  editForm: AcceptedOfferFormGroup = this.acceptedOfferFormService.createAcceptedOfferFormGroup();

  constructor(
    protected acceptedOfferService: AcceptedOfferService,
    protected acceptedOfferFormService: AcceptedOfferFormService,
    protected financeRequestService: FinanceRequestService,
    protected anchorTraderService: AnchorTraderService,
    protected financePartnerService: FinancePartnerService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareFinanceRequest = (o1: IFinanceRequest | null, o2: IFinanceRequest | null): boolean =>
    this.financeRequestService.compareFinanceRequest(o1, o2);

  compareAnchorTrader = (o1: IAnchorTrader | null, o2: IAnchorTrader | null): boolean =>
    this.anchorTraderService.compareAnchorTrader(o1, o2);

  compareFinancePartner = (o1: IFinancePartner | null, o2: IFinancePartner | null): boolean =>
    this.financePartnerService.compareFinancePartner(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ acceptedOffer }) => {
      this.acceptedOffer = acceptedOffer;
      if (acceptedOffer) {
        this.updateForm(acceptedOffer);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const acceptedOffer = this.acceptedOfferFormService.getAcceptedOffer(this.editForm);
    if (acceptedOffer.id !== null) {
      this.subscribeToSaveResponse(this.acceptedOfferService.update(acceptedOffer));
    } else {
      this.subscribeToSaveResponse(this.acceptedOfferService.create(acceptedOffer));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAcceptedOffer>>): void {
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

  protected updateForm(acceptedOffer: IAcceptedOffer): void {
    this.acceptedOffer = acceptedOffer;
    this.acceptedOfferFormService.resetForm(this.editForm, acceptedOffer);

    this.financeRequestsSharedCollection = this.financeRequestService.addFinanceRequestToCollectionIfMissing<IFinanceRequest>(
      this.financeRequestsSharedCollection,
      acceptedOffer.financerequest,
    );
    this.anchorTradersSharedCollection = this.anchorTraderService.addAnchorTraderToCollectionIfMissing<IAnchorTrader>(
      this.anchorTradersSharedCollection,
      acceptedOffer.anchortrader,
    );
    this.financePartnersSharedCollection = this.financePartnerService.addFinancePartnerToCollectionIfMissing<IFinancePartner>(
      this.financePartnersSharedCollection,
      acceptedOffer.financepartner,
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
            this.acceptedOffer?.financerequest,
          ),
        ),
      )
      .subscribe((financeRequests: IFinanceRequest[]) => (this.financeRequestsSharedCollection = financeRequests));

    this.anchorTraderService
      .query()
      .pipe(map((res: HttpResponse<IAnchorTrader[]>) => res.body ?? []))
      .pipe(
        map((anchorTraders: IAnchorTrader[]) =>
          this.anchorTraderService.addAnchorTraderToCollectionIfMissing<IAnchorTrader>(anchorTraders, this.acceptedOffer?.anchortrader),
        ),
      )
      .subscribe((anchorTraders: IAnchorTrader[]) => (this.anchorTradersSharedCollection = anchorTraders));

    this.financePartnerService
      .query()
      .pipe(map((res: HttpResponse<IFinancePartner[]>) => res.body ?? []))
      .pipe(
        map((financePartners: IFinancePartner[]) =>
          this.financePartnerService.addFinancePartnerToCollectionIfMissing<IFinancePartner>(
            financePartners,
            this.acceptedOffer?.financepartner,
          ),
        ),
      )
      .subscribe((financePartners: IFinancePartner[]) => (this.financePartnersSharedCollection = financePartners));
  }
}
