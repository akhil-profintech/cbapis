import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ICreditBazaarIntegrator } from '../credit-bazaar-integrator.model';
import { CreditBazaarIntegratorService } from '../service/credit-bazaar-integrator.service';
import { CreditBazaarIntegratorFormService, CreditBazaarIntegratorFormGroup } from './credit-bazaar-integrator-form.service';

@Component({
  standalone: true,
  selector: 'jhi-credit-bazaar-integrator-update',
  templateUrl: './credit-bazaar-integrator-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CreditBazaarIntegratorUpdateComponent implements OnInit {
  isSaving = false;
  creditBazaarIntegrator: ICreditBazaarIntegrator | null = null;

  editForm: CreditBazaarIntegratorFormGroup = this.creditBazaarIntegratorFormService.createCreditBazaarIntegratorFormGroup();

  constructor(
    protected creditBazaarIntegratorService: CreditBazaarIntegratorService,
    protected creditBazaarIntegratorFormService: CreditBazaarIntegratorFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ creditBazaarIntegrator }) => {
      this.creditBazaarIntegrator = creditBazaarIntegrator;
      if (creditBazaarIntegrator) {
        this.updateForm(creditBazaarIntegrator);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const creditBazaarIntegrator = this.creditBazaarIntegratorFormService.getCreditBazaarIntegrator(this.editForm);
    if (creditBazaarIntegrator.id !== null) {
      this.subscribeToSaveResponse(this.creditBazaarIntegratorService.update(creditBazaarIntegrator));
    } else {
      this.subscribeToSaveResponse(this.creditBazaarIntegratorService.create(creditBazaarIntegrator));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICreditBazaarIntegrator>>): void {
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

  protected updateForm(creditBazaarIntegrator: ICreditBazaarIntegrator): void {
    this.creditBazaarIntegrator = creditBazaarIntegrator;
    this.creditBazaarIntegratorFormService.resetForm(this.editForm, creditBazaarIntegrator);
  }
}
