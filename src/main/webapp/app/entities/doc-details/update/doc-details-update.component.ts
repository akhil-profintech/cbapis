import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { IDocDetails } from '../doc-details.model';
import { DocDetailsService } from '../service/doc-details.service';
import { DocDetailsFormService, DocDetailsFormGroup } from './doc-details-form.service';

@Component({
  standalone: true,
  selector: 'jhi-doc-details-update',
  templateUrl: './doc-details-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DocDetailsUpdateComponent implements OnInit {
  isSaving = false;
  docDetails: IDocDetails | null = null;

  financeRequestsSharedCollection: IFinanceRequest[] = [];

  editForm: DocDetailsFormGroup = this.docDetailsFormService.createDocDetailsFormGroup();

  constructor(
    protected docDetailsService: DocDetailsService,
    protected docDetailsFormService: DocDetailsFormService,
    protected financeRequestService: FinanceRequestService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareFinanceRequest = (o1: IFinanceRequest | null, o2: IFinanceRequest | null): boolean =>
    this.financeRequestService.compareFinanceRequest(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ docDetails }) => {
      this.docDetails = docDetails;
      if (docDetails) {
        this.updateForm(docDetails);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const docDetails = this.docDetailsFormService.getDocDetails(this.editForm);
    if (docDetails.id !== null) {
      this.subscribeToSaveResponse(this.docDetailsService.update(docDetails));
    } else {
      this.subscribeToSaveResponse(this.docDetailsService.create(docDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocDetails>>): void {
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

  protected updateForm(docDetails: IDocDetails): void {
    this.docDetails = docDetails;
    this.docDetailsFormService.resetForm(this.editForm, docDetails);

    this.financeRequestsSharedCollection = this.financeRequestService.addFinanceRequestToCollectionIfMissing<IFinanceRequest>(
      this.financeRequestsSharedCollection,
      docDetails.financeRequest,
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
            this.docDetails?.financeRequest,
          ),
        ),
      )
      .subscribe((financeRequests: IFinanceRequest[]) => (this.financeRequestsSharedCollection = financeRequests));
  }
}
