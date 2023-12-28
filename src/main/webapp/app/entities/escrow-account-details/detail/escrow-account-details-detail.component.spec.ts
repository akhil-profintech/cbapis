import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { EscrowAccountDetailsDetailComponent } from './escrow-account-details-detail.component';

describe('EscrowAccountDetails Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EscrowAccountDetailsDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: EscrowAccountDetailsDetailComponent,
              resolve: { escrowAccountDetails: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(EscrowAccountDetailsDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load escrowAccountDetails on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', EscrowAccountDetailsDetailComponent);

      // THEN
      expect(instance.escrowAccountDetails).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
