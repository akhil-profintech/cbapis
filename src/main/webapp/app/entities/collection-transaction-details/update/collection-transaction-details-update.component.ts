import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { DisbursementService } from 'app/entities/disbursement/service/disbursement.service';
import { IRepayment } from 'app/entities/repayment/repayment.model';
import { RepaymentService } from 'app/entities/repayment/service/repayment.service';
import { CollectionTransactionDetailsService } from '../service/collection-transaction-details.service';
import { ICollectionTransactionDetails } from '../collection-transaction-details.model';
import {
  CollectionTransactionDetailsFormService,
  CollectionTransactionDetailsFormGroup,
} from './collection-transaction-details-form.service';

@Component({
  standalone: true,
  selector: 'jhi-collection-transaction-details-update',
  templateUrl: './collection-transaction-details-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CollectionTransactionDetailsUpdateComponent implements OnInit {
  isSaving = false;
  collectionTransactionDetails: ICollectionTransactionDetails | null = null;

  disbursementsSharedCollection: IDisbursement[] = [];
  repaymentsSharedCollection: IRepayment[] = [];

  editForm: CollectionTransactionDetailsFormGroup =
    this.collectionTransactionDetailsFormService.createCollectionTransactionDetailsFormGroup();

  constructor(
    protected collectionTransactionDetailsService: CollectionTransactionDetailsService,
    protected collectionTransactionDetailsFormService: CollectionTransactionDetailsFormService,
    protected disbursementService: DisbursementService,
    protected repaymentService: RepaymentService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareDisbursement = (o1: IDisbursement | null, o2: IDisbursement | null): boolean =>
    this.disbursementService.compareDisbursement(o1, o2);

  compareRepayment = (o1: IRepayment | null, o2: IRepayment | null): boolean => this.repaymentService.compareRepayment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ collectionTransactionDetails }) => {
      this.collectionTransactionDetails = collectionTransactionDetails;
      if (collectionTransactionDetails) {
        this.updateForm(collectionTransactionDetails);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const collectionTransactionDetails = this.collectionTransactionDetailsFormService.getCollectionTransactionDetails(this.editForm);
    if (collectionTransactionDetails.id !== null) {
      this.subscribeToSaveResponse(this.collectionTransactionDetailsService.update(collectionTransactionDetails));
    } else {
      this.subscribeToSaveResponse(this.collectionTransactionDetailsService.create(collectionTransactionDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICollectionTransactionDetails>>): void {
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

  protected updateForm(collectionTransactionDetails: ICollectionTransactionDetails): void {
    this.collectionTransactionDetails = collectionTransactionDetails;
    this.collectionTransactionDetailsFormService.resetForm(this.editForm, collectionTransactionDetails);

    this.disbursementsSharedCollection = this.disbursementService.addDisbursementToCollectionIfMissing<IDisbursement>(
      this.disbursementsSharedCollection,
      collectionTransactionDetails.disbursement,
    );
    this.repaymentsSharedCollection = this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(
      this.repaymentsSharedCollection,
      collectionTransactionDetails.repayment,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.disbursementService
      .query()
      .pipe(map((res: HttpResponse<IDisbursement[]>) => res.body ?? []))
      .pipe(
        map((disbursements: IDisbursement[]) =>
          this.disbursementService.addDisbursementToCollectionIfMissing<IDisbursement>(
            disbursements,
            this.collectionTransactionDetails?.disbursement,
          ),
        ),
      )
      .subscribe((disbursements: IDisbursement[]) => (this.disbursementsSharedCollection = disbursements));

    this.repaymentService
      .query()
      .pipe(map((res: HttpResponse<IRepayment[]>) => res.body ?? []))
      .pipe(
        map((repayments: IRepayment[]) =>
          this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(repayments, this.collectionTransactionDetails?.repayment),
        ),
      )
      .subscribe((repayments: IRepayment[]) => (this.repaymentsSharedCollection = repayments));
  }
}
