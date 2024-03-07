import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';
import { AnchorTraderService } from 'app/entities/anchor-trader/service/anchor-trader.service';
import { IAnchorTraderPartner } from '../anchor-trader-partner.model';
import { AnchorTraderPartnerService } from '../service/anchor-trader-partner.service';
import { AnchorTraderPartnerFormService, AnchorTraderPartnerFormGroup } from './anchor-trader-partner-form.service';

@Component({
  standalone: true,
  selector: 'jhi-anchor-trader-partner-update',
  templateUrl: './anchor-trader-partner-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AnchorTraderPartnerUpdateComponent implements OnInit {
  isSaving = false;
  anchorTraderPartner: IAnchorTraderPartner | null = null;

  anchorTradersSharedCollection: IAnchorTrader[] = [];

  editForm: AnchorTraderPartnerFormGroup = this.anchorTraderPartnerFormService.createAnchorTraderPartnerFormGroup();

  constructor(
    protected anchorTraderPartnerService: AnchorTraderPartnerService,
    protected anchorTraderPartnerFormService: AnchorTraderPartnerFormService,
    protected anchorTraderService: AnchorTraderService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareAnchorTrader = (o1: IAnchorTrader | null, o2: IAnchorTrader | null): boolean =>
    this.anchorTraderService.compareAnchorTrader(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ anchorTraderPartner }) => {
      this.anchorTraderPartner = anchorTraderPartner;
      if (anchorTraderPartner) {
        this.updateForm(anchorTraderPartner);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const anchorTraderPartner = this.anchorTraderPartnerFormService.getAnchorTraderPartner(this.editForm);
    if (anchorTraderPartner.id !== null) {
      this.subscribeToSaveResponse(this.anchorTraderPartnerService.update(anchorTraderPartner));
    } else {
      this.subscribeToSaveResponse(this.anchorTraderPartnerService.create(anchorTraderPartner));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnchorTraderPartner>>): void {
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

  protected updateForm(anchorTraderPartner: IAnchorTraderPartner): void {
    this.anchorTraderPartner = anchorTraderPartner;
    this.anchorTraderPartnerFormService.resetForm(this.editForm, anchorTraderPartner);

    this.anchorTradersSharedCollection = this.anchorTraderService.addAnchorTraderToCollectionIfMissing<IAnchorTrader>(
      this.anchorTradersSharedCollection,
      anchorTraderPartner.anchortrader,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.anchorTraderService
      .query()
      .pipe(map((res: HttpResponse<IAnchorTrader[]>) => res.body ?? []))
      .pipe(
        map((anchorTraders: IAnchorTrader[]) =>
          this.anchorTraderService.addAnchorTraderToCollectionIfMissing<IAnchorTrader>(
            anchorTraders,
            this.anchorTraderPartner?.anchortrader,
          ),
        ),
      )
      .subscribe((anchorTraders: IAnchorTrader[]) => (this.anchorTradersSharedCollection = anchorTraders));
  }
}
