import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { ICBCREProcess } from '../cbcre-process.model';
import { CBCREProcessService } from '../service/cbcre-process.service';
import { CBCREProcessFormService, CBCREProcessFormGroup } from './cbcre-process-form.service';

@Component({
  standalone: true,
  selector: 'jhi-cbcre-process-update',
  templateUrl: './cbcre-process-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CBCREProcessUpdateComponent implements OnInit {
  isSaving = false;
  cBCREProcess: ICBCREProcess | null = null;

  financeRequestsSharedCollection: IFinanceRequest[] = [];

  editForm: CBCREProcessFormGroup = this.cBCREProcessFormService.createCBCREProcessFormGroup();

  constructor(
    protected cBCREProcessService: CBCREProcessService,
    protected cBCREProcessFormService: CBCREProcessFormService,
    protected financeRequestService: FinanceRequestService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareFinanceRequest = (o1: IFinanceRequest | null, o2: IFinanceRequest | null): boolean =>
    this.financeRequestService.compareFinanceRequest(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cBCREProcess }) => {
      this.cBCREProcess = cBCREProcess;
      if (cBCREProcess) {
        this.updateForm(cBCREProcess);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cBCREProcess = this.cBCREProcessFormService.getCBCREProcess(this.editForm);
    if (cBCREProcess.id !== null) {
      this.subscribeToSaveResponse(this.cBCREProcessService.update(cBCREProcess));
    } else {
      this.subscribeToSaveResponse(this.cBCREProcessService.create(cBCREProcess));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICBCREProcess>>): void {
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

  protected updateForm(cBCREProcess: ICBCREProcess): void {
    this.cBCREProcess = cBCREProcess;
    this.cBCREProcessFormService.resetForm(this.editForm, cBCREProcess);

    this.financeRequestsSharedCollection = this.financeRequestService.addFinanceRequestToCollectionIfMissing<IFinanceRequest>(
      this.financeRequestsSharedCollection,
      cBCREProcess.financeRequest,
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
            this.cBCREProcess?.financeRequest,
          ),
        ),
      )
      .subscribe((financeRequests: IFinanceRequest[]) => (this.financeRequestsSharedCollection = financeRequests));
  }
}
